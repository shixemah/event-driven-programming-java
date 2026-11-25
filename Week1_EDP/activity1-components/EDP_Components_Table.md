# Activity 1: Spot the Components

## Event-driven systems table

| System | Event Source | Event | Listener | Handler / Action |
|---|---|---|---|---|
| ATM Machine | Keypad button | Button pressed | ATM software | Verify PIN |
| ATM Machine | Card slot | Card inserted | Card reader | Read card data |
| ATM Machine | Cash dispenser | Cash taken | Sensor | End transaction |
| Microwave Oven | Door sensor | Door opened | Safety interlock | Stop cooking |
| Microwave Oven | Start button | Start pressed | Microwave controller | Start magnetron and countdown timer |
| Microwave Oven | Internal timer | Time expires | Microwave controller | Stop heating and beep |
| Traffic Light System | Timer | Time expires | Traffic controller | Change light (green -> yellow -> red) |
| Traffic Light System | Pedestrian button | Button pressed | Traffic controller | Schedule a pedestrian crossing phase |
| Traffic Light System | Road sensor (induction loop) | Vehicle detected | Traffic controller | Extend or trigger green phase |
| Video Game | Keyboard / gamepad | Key pressed | Input manager | Move character |
| Video Game | Collision detector | Player hits enemy | Game engine | Reduce health, play sound |
| Video Game | Game timer | Frame tick | Game loop | Update and redraw the screen |
| Elevator | Hall call button | Button pressed | Elevator controller | Dispatch nearest car |
| Elevator | Floor button inside car | Floor selected | Elevator controller | Queue floor, move car |
| Elevator | Door sensor | Beam interrupted | Door controller | Re-open doors |
| Smartphone touchscreen | Touch sensor | Tap / swipe | Operating system / app | Open app or scroll content |
| Netflix / YouTube | Play button | Button clicked | Video player | Start / pause streaming |
| Netflix / YouTube | Search box | Text entered | Search service | Fetch and show results |
| Online shopping website | "Buy" button | Button clicked | Order service | Process payment |
| Online shopping website | Payment gateway | Payment confirmed | Order service | Send confirmation email |
| Car dashboard | Fuel sensor | Fuel level low | Dashboard controller | Light the warning indicator |
| Car dashboard | Seat-belt sensor | Belt unfastened | Dashboard controller | Sound chime and show icon |
| Smart TV remote | Remote button | Volume up pressed | TV software | Increase volume, show on-screen bar |
| Security alarm system | Motion sensor | Motion detected | Alarm controller | Sound siren, notify owner |
| Self-checkout | Barcode scanner | Item scanned | Checkout software | Look up price, add to cart |
| Self-checkout | Scale | Weight changed | Checkout software | Verify item weight |

The first five systems are the ones from the videos; the rest are the extra systems from Step 3 (more than the 8 required).

## Self-check

**1. What is the difference between an event source and an event listener?**
The event source is the object that generates the event (a button, a timer, a sensor). The event listener is the object registered with the source that waits for the event and is notified when it happens. The source says "something happened"; the listener's handler method contains the code that reacts.

**2. Can one event source have multiple listeners? Give an example.**
Yes. A "Buy" button on a shopping website can notify an order listener (process payment), an inventory listener (reduce stock) and an analytics listener (record the click). In Swing, a `JButton` can have many `ActionListener`s added with `addActionListener`.

**3. Can one listener handle events from multiple sources? Give an example.**
Yes. A single ATM controller listens to the keypad, card reader and cash dispenser. In Swing, one `ActionListener` can be attached to many buttons and tell them apart with `e.getSource()` or `e.getActionCommand()`.
