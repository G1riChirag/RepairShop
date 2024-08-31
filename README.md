# Repair Shop App

## Overview

The Repair Shop App is an Android application designed to help users manage their vehicle repair records. This project was developed as a submission for an assignment in DePauw University's Mobile App Development course. The app utilizes SQLite for local database operations, allowing users to perform Create, Read, and Delete (CRD) operations on their vehicle and repair records.

## Features

1. **Main Activity**
   - Opens respective activities based on user selection:
     - "Add New Vehicle" opens the `AddVehicleActivity`.
     - "Add New Repair" opens the `AddRepairActivity`.
     - "Search Repairs" opens the `SearchRepairsActivity`.

2. **Add Vehicle Activity**
   - Allows users to enter details about their vehicles:
     - Year
     - Make and Model
     - Purchase Price
     - Is New (True/False)
   - Adds a new vehicle record to the SQLite database.
   - Displays a Toast message upon successful addition of a vehicle.

3. **Add Repair Activity**
   - Users can enter details about a repair:
     - Select a vehicle from a Spinner populated with all vehicles.
     - Choose a repair date using a DatePicker.
     - Enter repair cost and description.
   - Adds a new repair record to the SQLite database.
   - Displays a Toast message upon successful addition of a repair.

4. **Search Repairs Activity**
   - Users can search for repairs based on a description keyword.
   - Results are displayed in a ListView showing both vehicle and repair information.
   - Supports deletion of repair records via long-click on ListView rows.

## Installation

1. Clone this repository to your local machine:
   ```bash
   git clone https://github.com/yourusername/repair-shop-app.git
2. Open the project in Android Studio.

3. Build and run the project on an Android emulator or device.


