💧 MaxOut – Smart Water Tracker Android App

MaxOut is a clean, modern, and easy-to-use Android application built using Kotlin. It helps users track their daily water intake, stay hydrated, and maintain healthy habits. The app includes a smart reminder system, a BMI calculator, and customizable hydration goals.


## ✨ Features

- **Track Water Intake**: Add 250ml or 500ml in a single tap.
- **Custom Daily Goal**: Update your hydration target anytime from the menu.
- **BMI Calculator**: Enter your height and weight to get your BMI and health status.
- **Smart Notifications**: Automatically reminds you to drink water at regular intervals using WorkManager.
- **Persistent Storage**: Stores your data even if you close the app, using SharedPreferences.
- **Dark Mode Friendly UI**: Clean, responsive, and modern design with support for custom fonts and themes.


## 🧰 Built With

- **Kotlin**
- **Android Jetpack (WorkManager, AppCompat, ConstraintLayout)**
- **SharedPreferences**
- **Material Design Components**
- **Notification Channels (Android O+)**



## 🛠️ Getting Started

### Prerequisites
- Android Studio Flamingo or later
- Android SDK 21+
- Kotlin 1.8+

### Installation
1. Clone the repository:
  
   git clone https://github.com/your-username/maxout.git
Open in Android Studio:


File > Open > Navigate to the cloned folder
Build and run the app on an emulator or real device.

📂 Project Structure


├── MainActivity.kt               // Core UI and logic
├── BMICalculatorActivity.kt     // BMI calculator feature
├── UpdateGoalActivity.kt        // Set hydration goal
├── WaterReminderWorker.kt       // Periodic reminder notifications
├── res/layout                   // XML UI files
├── res/menu                     // Options menu XML
├── drawable                     // Custom button backgrounds
📌 Future Enhancements
Sync with Firebase for cloud backup

Weekly/Monthly water intake history & stats

Voice reminders and achievements

🤝 Contributing
Pull requests are welcome! For major changes, please open an issue first to discuss what you’d like to change.

📄 License
This project is open-source. You are free to use and modify it for learning and non-commercial purposes. For commercial use, please ask for permission.

🙋‍♂️ Author
Developed with ❤️ by Rahul Dutta

Feel free to connect with me on [LinkedIn](https://www.linkedin.com/in/imrahul16) or check out my other projects on [GitHub](https://github.com/imrahul16)
