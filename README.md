# Edge Light

Current State:
- [X] Notifications are being sent when the screen is off
- [X] System applications are being ignored (to prevent sending charging notifications)

Plans:
- [ ] Make it possible to toggle which apps that should activate Edge Lightning 
- [ ] Being able to change the contents of the notification
- [ ] Figure out why sometimes a notification is sent without any information from the original notification
- [ ] Look more into the power consumption of the app
- [ ] Look into a workaround for setting the color of the Edge Lightning effect

## Why is this App needed?
With the removal of the LED-light Samsung introduced Edge Lightning which could be used as a replacement to give a visual signal that a notification was received. However, their implementation could be considered incomplete since it does not work with every app out of the box if the AOD is not enabled. This problem is what this App aims to solve, having AOD set to off and still being able to use Edge Lightning.

## What is this App?
This app acts as a small middle layer between applications running on your android phone and Samsungs Edge Lightning display. This makes it possible for every application on your phone to send notifications that appear on the edge lightning even when the display is off.

## How does it work?
This application works by using 'Notification access' to copy the contents of any notification and resends it while activating the Edge Lightning. This means that every notification is sent twice, however, the notification sent by EdgeLight is just to trigger the Edge Lightning effect so it disappears after 1ms and makes no sound which gives the illusion that only a single notification was ever sent.
