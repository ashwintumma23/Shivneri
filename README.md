# Shivneri: Failure Resistant Email Service

Shivneri, is an email service which is resistant to failures of underlying email services. Business now rely on external email services like, [Mailgun](https://mailgun.com), [Sendgrid](https://sendgrid.com/), etc. for sending emails to customers, instead of home grown SMTP services. Shivneri, contains a load balancer, and request dispatcher which routes email traffic to such underlying email services, and abstracts all the underlying email service details from the top level. It also takes care of failures of underlying services, by meticulously balancing the email loads.

[![Stories in progress](https://badge.waffle.io/ashwintumma23/Shivneri.svg?label=In%Progress&title=In Progress)](http://waffle.io/ashwintumma23/Shivneri)

If you want to jump ahead, here are a few direct links to: [Design Considerations and Features](https://github.com/ashwintumma23/Shivneri/#design-considerations-and-features), and [Live Demo](https://github.com/ashwintumma23/Shivneri/#demonstration)

### On what does this solution focus?
This solution primarily focuses on **backend engineering** of the email service, and shown in the Architecture diagram below.

### Why is this tool christened Shivneri?
[Shiveri](https://en.wikipedia.org/wiki/Shivneri) is the name of an Indian land fort in the state of Maharashtra, my home state. It is the birth place of an Indian King and statesman named, Chhatrapati Shivaji Maharaj, who led the genesis of Maratha empire. The reason I choose the tool to name Shivneri is that, it signifies birth, and I consider this tool as the birth of my distributed systems work. Also, I learnt the art of naming the tool as something meaningful from this repository: [Raigad](https://github.com/Netflix/Raigad), which is Netflix's OSS.

### Motivation 
This project sources its inspiration from Uber's Coding [Challenge](https://github.com/uber/coding-challenge-tools/blob/master/coding_challenge.md). I have always been amazed by the concept of emails. I believe that writing great email is an art, and I vividly remember that I have been making assisduous endeavor to imbibe that art in me since many years. Perhaps this is the reason why, I was so attracted to this problem, and chose to work on my implementation of it.

### Problem Description
Desigining of a reliable email service which accepts email, and distributes the actual 'sending' activity of that email to underlying email services. If one underlying service goes down, all the traffic is failed over to the other service, and all of this happens without the knowledge of the higher level services. 

### Design Considerations and Features
This section elaborates upon the design considerations and the features of the tool. The following architecture diagram is used for explication purposes.
![Shivneri Architecture](https://github.com/ashwintumma23/Shivneri/blob/master/images/ShivneriArchitecture.jpg "Shivneri Architecture")

To reiterate the disclaimer, the logos in the above diagram are used only for representational purposes, the copyrights and trademarks vest with the respective owners. `Email Content` forms the actual email content which needs to be sent to the end user/ customer. The `Main Server` process is the heart of the entire system. It acts as load balancer, and request dispatcher as well. When the process starts, it spawns threads for: 
* Listening for Email requests from higher level
* Listening for heart beats from underlying email services
* Monitoring the heart beats of the underlying email services

The `Underlying email services`, send heart beats to the Main Server. Any service can come up, and start sending the heart beat. If the heart beat is not registered with the Main Server, it gets its registered and starts monitoring that email service. N number of services can be added in this way. Each Email content request is received by the Main Server, and the request is then passed on to any of the `Active` underlying email service. By Active, we mean that, that the service is operational, and heart beats are still active. The underlying service in turn takes care of sending the email using its own API. Once again, the code is designed in a `modular` fashion, such that any new service can be added very easily simply by extending the cases, and adding its class along with required API details of that particular service. In the diagram above, we see that how Amazon's SES can be added on the fly, and the Main server in turn starts monitoring the service. 

Whenever any underlying email service goes down, the Main server comes to know that the service is not operational, so it: 
* Removes that service from the list of `Actively` monitored services, and
* Fails over all the email traffic to the other active email services.
Also, in case the underlying service, again comes up and sends heart beats, the Main server again adds it to the list of active services, and starts monitoring it once again. The video demonstration shows this case in depth. 

### Tests
This section highlights of how the tests were done for validation of load balancing, request dispatching and failovers. The Server process is initiated, along with the Mailgun and Sendgrid processes. Some email traffic is allowed to follow through the system, and then a process, say, Mailgun is killed; which means that the Mailgun service has gone down. Now, the Main Server sees that it has not received heart beats from Mailgun, so it concludes that the service has gone down, and removes it from the list of active services. All the traffic is automatically redirected to Sendgrid service. Again, when the Mailgun service comes up, the traffic is distributed between the two. The video demonstration shows this case in depth. 

### Demonstration
Being a purely backend engineering tool, it is not that helpful to have the tool deployed on Amazon EC2, or any other hosting site. That said, the tool was tried and tested on EC2 instance. Here's a video recording that I made for a live demonstration of the tool.

###### Fullscreen Playback Recommended

[![Shivneri Demo](https://img.youtube.com/vi/GpbvDu2-KhA/0.jpg)](https://youtu.be/GpbvDu2-KhA)

Direct Link: https://youtu.be/GpbvDu2-KhA


### What additional features can be implemented? 
Given more time and efforts the following features could have been implemented: 
* **Modularized Scheduler**: Currently the scheduler is random. A random value is picked up and the request is dispatched to that random valued underlying email service. Round robin scheduler can be implemented using a Circular Queue. O(1) scheduler from Linux Kernel 2.6 can be implemented using HashMaps and simple queues. And all these can be modularized. The implementor of this system is free to choose any scheduler
* Graphical User Interface for specifying email input parameters


### Author
Ashwin Tumma

Computer Science Graduate Student

Stony Brook University, New York

[Website](https://sites.google.com/site/ashwintumma23/) | [Resume](https://sites.google.com/site/ashwintumma23/resume) | [LinkedIn](https://www.linkedin.com/in/ashwintumma23)
