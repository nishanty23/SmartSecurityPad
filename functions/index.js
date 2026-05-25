const functions = require("firebase-functions");
const admin = require("firebase-admin");

admin.initializeApp();

/**
 * Cloud Function: watchPadStatus
 *
 * Triggers whenever data at devices/PAD_001 changes in Realtime Database.
 * If the new status is "ALERT", sends an FCM push notification to ALL
 * subscribed devices (anyone who has the app installed and is subscribed
 * to the "PAD_001_alerts" topic).
 */
exports.watchPadStatus = functions.database
    .ref("/devices/PAD_001")
    .onUpdate(async (change, context) => {

        const before = change.before.val(); // data before the update
        const after  = change.after.val();  // data after the update

        // Only fire when status transitions TO "ALERT" (avoid duplicate notifications)
        if (after.status !== "ALERT" || before.status === "ALERT") {
            console.log("No alert transition detected. Skipping notification.");
            return null;
        }

        const weight = after.weight ?? 0;

        console.log(`ALERT detected on PAD_001 — Weight: ${weight}g. Sending FCM...`);

        // Build the FCM notification payload
        const message = {
            notification: {
                title: "⚠ Security Alert! — PAD_001",
                body: `Intrusion detected! Weight reading: ${weight}g`,
            },
            android: {
                priority: "high",
                notification: {
                    sound: "default",
                    channelId: "smart_security_alerts", // must match the channel in the app
                    color: "#C62828",
                    icon: "ic_warning",
                    vibrateTimingsMillis: ["0", "400", "200", "400"],
                    defaultVibrateTimings: false,
                },
            },
            apns: {
                payload: {
                    aps: {
                        sound: "default",
                        badge: 1,
                    },
                },
            },
            // Send to everyone subscribed to this topic
            topic: "PAD_001_alerts",
        };

        try {
            const response = await admin.messaging().send(message);
            console.log("FCM notification sent successfully:", response);
        } catch (error) {
            console.error("Error sending FCM notification:", error);
        }

        return null;
    });