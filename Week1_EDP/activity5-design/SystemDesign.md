# Event-Driven System Design

## System Name: Parking Garage

---

## Part 1: Event Sources

1. Entry gate sensor - generates "car arrived" events
2. Exit gate sensor - generates "car leaving" events
3. Payment kiosk - generates "ticket paid" events
4. Space occupancy sensors - generate "space taken / space freed" events
5. System timer - generates "clock tick" events (updates time and parking fees)

---

## Part 2: Events

| Event Name | Source | Description |
|---|---|---|
| CarArrived | Entry gate sensor | A vehicle stops at the entry barrier and requests a ticket |
| CarLeaving | Exit gate sensor | A vehicle reaches the exit barrier |
| TicketPaid | Payment kiosk | A driver pays the parking fee |
| GarageFull | Occupancy sensors | The last free space is taken |
| ClockTick | System timer | One second passes; the clock and fees are refreshed |

---

## Part 3: Event Handlers

| Event | Handler Action |
|---|---|
| CarArrived | If spaces are free: issue a ticket, raise the entry barrier, decrease free spaces. If full: show "GARAGE FULL" and keep the barrier down |
| CarLeaving | If the ticket is paid: raise the exit barrier and increase free spaces. If unpaid: keep the barrier down and show "Please pay first" |
| TicketPaid | Calculate the fee, mark the ticket as paid, print the receipt |
| GarageFull | Turn on the "FULL" sign and block the entry barrier |
| ClockTick | Update the displayed time and refresh the status panel |

---

## Part 4: Event Flow Diagram

```
Car at entry -> Entry sensor (source) -> CarArrived event -> Event Queue
     -> Event Loop (EDT) -> ActionListener -> Handler: issue ticket, update spaces
     -> GUI update (free spaces label, log, barrier status)

Timer (source) -> ClockTick event -> Event Queue -> Event Loop -> ActionListener
     -> Handler: refresh clock -> GUI update
```

---

## Part 5: Potential Issues

1. Two cars triggering events at the same moment could corrupt the free-space count - process all events one at a time on the event loop (queue) so the counter is only changed by one handler at a time.
2. A driver tries to leave without paying - the exit handler checks the ticket's paid status before raising the barrier.
3. A slow operation (e.g. a payment gateway call) could freeze the interface - run it on a background thread and update the GUI with `SwingUtilities.invokeLater()`.
