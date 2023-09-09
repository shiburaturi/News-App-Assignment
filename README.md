# News-App-Assignment

# App Readme

## Introduction
Welcome! This is a Kotlin-based Android application that provides a convenient way to stay up-to-date with the latest news headlines. In this README, we'll cover how to use the app and highlight its key functionalities.

## Table of Contents
- [Getting Started]
- [Implemented Functionalities]
- [Architecture]

## Getting Started
Before using the App, please ensure that you have an active internet connection on your device.

To install the app:
1. Download the APK from News-App-Assignment.
2. Install the APK on your Android device.
3. Open the app and start exploring the latest news headlines!

## Implemented Functionalities
The app offers the following functionalities:

### 1. Showing All Headlines in List View
Upon opening the app, you'll be presented with a list view containing all the latest news headlines. Each headline is displayed as a card with relevant information.

### 2. WebView for News URL
When you tap on a news card, the app will open a WebView to load the full news article from its URL. This feature lets you read the complete news stories within the app's interface.

### 3. Search News
MyApp enables you to search for news using both source description and title. Enter your search query in the search bar, and the app will provide you with relevant news articles matching your criteria.

### 4. Default Image for Missing Articles
In cases where a news article does not provide an image, MyApp ensures a default image is displayed.

## Architecture
App is developed using Kotlin and follows a well-defined architecture to ensure maintainability and scalability. The app architecture is designed to separate concerns and make the codebase clean and organized. It includes:

- **MVVM (Model-View-ViewModel) Architecture**: The app follows the MVVM architecture pattern, which separates the app into three main components:
  - Model: Represents the data and business logic.
  - View: Represents the UI components and layout.
  - ViewModel: Acts as an intermediary between the Model and View, handling user interactions and providing data to the UI.

- **Clean Code Principles**: The codebase adheres to clean code principles, making it easy to read, understand, and maintain.

- **Kotlin**: The app is written entirely in Kotlin, taking advantage of its modern and concise features to enhance code quality.

Thank you for using the App, and we hope you enjoy staying informed with the latest news!
