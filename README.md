# Simple Date Picker

Designed for simplicity and ease of integration, it offers a user-friendly date picker that elegantly slides up from the bottom of the screen. Perfect for modern Android applications, it provides a sleek, intuitive interface for capturing dates without disrupting the user experience. Give it a shot and enhance your app's functionality today!
<br />
<br />
<img src="https://github.com/NallDev/Custom-Date-Picker/assets/90769828/74fe9e57-8463-4284-87c2-3d9b3571af02" alt="Custom Date Picker" width="300" height="640">

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Listeners](#listeners)
- [Contributing](#contributing)

## Installation

To integrate Date Picker into your Android project, add the following configuration to your `settings.gradle` file:

```gradle
maven {
    setUrl("https://jitpack.io")
}
```

Then, you can add configuration to your `build.gradle` in app module:
```gradle
implementation("com.github.NallDev:Custom-Date-Picker:1.0.0")
```

## Usage

Follow these steps to integrate the date picker into your Android application.

1. Create an Instance of NalDatePicker
```kotlin
private val datePicker by lazy { NalDatePicker() }
```

2. Display the Date Picker
```kotlin
datePicker.show(supportFragmentManager, "datePicker")
```

## Listeners

This date picker provides two listeners to handle events
Name  | Description |
------------- | -------------
`onSaveListener` | Triggered when the save button is clicked. It returns the selected date as a string in the format "dd/MM/YYYY".
`onDateChangeListener` | Triggered when there is a change in the selected date. It also returns the date as a string in the format "dd/MM/YYYY".

**Setting up onSaveListener**\
Attach this listener to handle the event when the save button is pressed:
```kotlin
datePicker.onSaveListener = { dateString ->
    // Handle the saved date string (dd/MM/YYYY)
    Toast.makeText(this, "Date saved: $dateString", Toast.LENGTH_SHORT).show()
}
```

**Setting up onDateChangeListener**\
Attach this listener to handle any changes in the date selection:
```kotlin
datePicker.onDateChangeListener = { dateString ->
    // Handle the changed date string (dd/MM/YYYY)
    Toast.makeText(this, "Date changed to: $dateString", Toast.LENGTH_SHORT).show()
}
```

## Contributing
Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are greatly appreciated.
