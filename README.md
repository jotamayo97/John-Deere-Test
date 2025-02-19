An 8-hours John Deere Code Challenge. 

After starting the application, access RabbitMQ’s management interface to test the queues and streams.

Go to http://localhost:15672/#/.  
Navigate to Queues and Streams.  
You can add messages for **sessions** or **events** by pushing them into the relevant queues.  
Make sure each message follows the specified structure:  

## Structure of Messages

### Session Message

Each session message must adhere to the following structure:

```json
{
    "sessionId": "xyz123-uvw456-rst789",
    "machineId": "machine-002",
    "startAt": "1684330000"
}
```

### Event Message

Each event message must adhere to the following structure:

```json
{
    "sessionId": "xyz123-uvw456-rst789",
    "events": [
        {
            "eventAt": "1684340000",
            "eventType": "engineLoad",
            "numericEventValue": 85.0
        },
        {
            "eventAt": "1684343600",
            "eventType": "coolantTemperature",
            "numericEventValue": 92.3
        },
        {
            "eventAt": "1684347200",
            "eventType": "speed",
            "numericEventValue": 88.5
        }
    ]
}
```
## Test endpoint
You can test the especified endpoints like this:

GET http://localhost:8080/events/machine/2343-asdf-fads/session/12faffbb-882a-439d-a31c-c0e37bb4a376  
GET http://localhost:8080/machines
