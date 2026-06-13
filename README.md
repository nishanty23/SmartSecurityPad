# Smart Security Pad 🔐📱

Smart Security Pad is an IoT-based item protection and theft detection system designed to provide real-time monitoring and instant alerts for valuable objects. Unlike traditional security systems that monitor only surrounding areas, this project focuses on protecting the object itself using sensors and mobile notifications.

---

## 🚀 Features

- Real-time item monitoring
- Pressure and movement detection
- Instant mobile notifications
- Firebase integration
- Android application support
- Wi-Fi based communication
- Portable and low-cost solution
- User-friendly interface

---

## 🏗️ Application Flow

```mermaid
flowchart TD
    A[Launch App] --> B[User Login]
    B --> C[Home Dashboard]

    C --> D[View System Status]
    C --> E[View Assets]
    C --> F[View Profile]

    E --> G[Select Asset]
    G --> H[View Asset Details]
    H --> I{Asset Secure?}

    I -->|Yes| J[Display Safe Status]
    I -->|No| K[Generate Security Alert]

    K --> L[Send Notification]
    L --> M[Update Alert History]

    J --> C
    M --> C

    F --> N[Manage Settings]
    N --> O[Logout]
```

---

## 🛠️ Technologies Used

### Hardware
- ESP8266 / Arduino
- Pressure Sensor
- Motion Sensor
- Wi-Fi Module

### Software
- Android Studio
- Java / Kotlin
- Firebase
- IoT APIs

---

## 📱 Mobile Application

The Android application allows users to:
- Monitor item status in real-time
- Receive instant theft alerts
- Track unauthorized movement detection
- Connect with the IoT device through Firebase

---

## ⚙️ Working Principle

1. User places an item on the smart pad
2. Sensors record baseline pressure
3. System continuously monitors movement/activity
4. If movement exceeds the threshold:
   - Microcontroller processes the signal
   - Alert is sent through Wi-Fi
   - User receives an instant notification

---

## 🧩 System Architecture

```mermaid
flowchart LR
    A["Sensors<br/>Motion / Tilt / Vibration Detection"]
    B["ESP8266 / Arduino<br/>Data Collection & Processing"]
    C["Wi-Fi Communication<br/>Wireless Data Transmission"]
    D["Firebase Cloud<br/>Realtime Database & Event Processing"]
    E["Android Application<br/>Monitoring Dashboard"]
    F["Push Notifications<br/>Security Alerts to User"]

    A --> B
    B --> C
    C --> D
    D --> E
    D --> F
```

---

## 🧪 Testing

The project was tested using:
- Unit Testing
- Integration Testing
- System Testing
- User Acceptance Testing

### Results
- Response Time: 1–3 seconds
- Stable operation
- High detection accuracy

---

## 📌 Applications

- Libraries
- Offices
- Cafes
- Classrooms
- Public Spaces

---

## 📷 Application Walkthrough

| Splash Screen | Home Page Safe | Home Page Alert |
|-----------|-----------|--------------|
| <img src="Screenshots/Splash-Screen.jpg" width="250"> | <img src="Screenshots/Home-Page-Item-Safe.jpg" width="250"> | <img src="Screenshots/Home-Page-Item-Alert.jpg" width="250"> |

| Assets | Laptop  | Laptop |
|-----------|-----------|--------------|
| <img src="Screenshots/View-Assets-Page.jpg" width="250"> | <img src="Screenshots/Asset-Details-Page-Laptop-2.jpg" width="250"> | <img src="Screenshots/Asset-Details-Page-Laptop-1.jpg" width="250"> |

| Backpack | Backpack | Add Asset |
|-----------|-----------|--------------|
| <img src="Screenshots/Asset-Details-Page-Backpack-1.jpg" width="250"> | <img src="Screenshots/Asset-Details-Page-Backpack-2.jpg" width="250"> | <img src="Screenshots/Add-Asset-Page.jpg" width="250"> |

| Profile |
|-----------|
| <img src="Screenshots/Profile-Page.jpg" width="250"> |

---

## 🎥 Project Demonstrations

Watch the complete application workflow below:

| 📱 Android App Demo | 🔧 Hardware Demo |
|--------------------|------------------|
| https://github.com/user-attachments/assets/54c265dd-84bb-4d23-88f0-405d80708534 | https://github.com/user-attachments/assets/4fe8a496-930a-47b4-97fb-64ff49987d51 |

---

## 🔮 Future Scope

- AI-based anomaly detection
- TinyML integration
- Blockchain-secured alerts
- Cloud synchronization
- GPS tracking integration

---
