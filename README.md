# Shivneri: Failure Resistant Email Service

Shivneri, is an email service which is resistant to failures of underlying email services. Business now rely on external email services like, [Mailgun](https://mailgun.com), [Sendgrid](https://sendgrid.com/), etc. for sending emails to customers, instead of home grown SMTP services. Shivneri, contains a load balancer, and request dispatcher which routes email traffic to such underlying email services, and abstracts all the underlying email service details from the top level. It also takes care of failures of underlying services, by meticulously balancing the email loads.

If you want to jump ahead, here are a few direct links to: [Design Considerations and Features](https://github.com/ashwintumma23/Shivneri/#design-considerations-and-features), and [Live Demo](https://github.com/ashwintumma23/Shivneri/#demonstration)

### Why is this tool christened Shivneri?
[Shiveri](https://en.wikipedia.org/wiki/Shivneri) is the name of an Indian land fort in the state of Maharashtra, my home state. It is the birth place of an Indian King and statesman named, Chhatrapati Shivaji Maharaj, who led the genesis of Maratha empire. The reason I choose the tool to name Shivneri is that, it signifies birth, and I consider this tool as the birth of my distributed systems work. Also, I learnt the art of naming the tool as something meaningful from this repository: [Raigad](https://github.com/Netflix/Raigad), which is Netflix's OSS.

### Motivation 
This project sources its inspiration from Uber's Coding [Challenge](https://github.com/uber/coding-challenge-tools/blob/master/coding_challenge.md). I have always been amazed by the concept of emails. I believe that writing great email is an art, and I vividly remember that I have been making assisduous endeavor to imbibe that art in me since many years. Perhaps this is the reason why, I was so attracted to this problem, and chose to work on my implementation of it.

### Problem Description
Desigining of a reliable email service which accepts email, and distributes the actual 'sending' activity of that email to underlying email services. If one underlying service goes down, all the traffic is failed over to the other service, and all of this happens without the knowledge of the higher level services. 

### Design Considerations and Features
This section elaborates upon the design considerations and the features of the tool. The following architecture diagram is used for explication purposes.
![Shivneri Architecture](https://github.com/ashwintumma23/Shivneri/blob/master/images/ShivneriArchitecture.jpg "Shivneri Architecture")

To reiterate the disclaimer, the logos in the above diagram are used only for representational purposes, the copyrights and trademarks vest with the respective owners. `Email Content` forms the actual email content which needs to be sent to the end user/ customer.

### Demonstration
Being a purely backend engineering tool, it is not that helpful to have the tool deployed on Amazon EC2, or any other hosting site. That said, the tool was tried and tested on EC2 instance. Here's a video recording that I made for a live demonstration of the tool.

###### Fullscreen Playback Recommended

[![Shivneri Demo](https://img.youtube.com/vi/GpbvDu2-KhA/0.jpg)](https://youtu.be/GpbvDu2-KhA)

Direct Link: https://youtu.be/GpbvDu2-KhA
