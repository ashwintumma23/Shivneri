# Shivneri: Failure Resistant Email Service

Shivneri, is an email service which is resistant to failures of underlying email services. Business now rely on external email services like, [Mailgun](https://mailgun.com), [Sendgrid](https://sendgrid.com/), etc. for sending emails to customers, instead of home grown SMTP services. Shivneri, contains a load balancer, and request dispatcher which routes email traffic to such underlying email services, and abstracts all the underlying email service details from the top level. It also takes care of failures of underlying services, by meticulously balancing the email loads.

### Why is this tool christened Shivneri?
[Shiveri](https://en.wikipedia.org/wiki/Shivneri) is the name of an Indian land fort in the state of Maharashtra, my home state. It is the birth place of an Indian King and statesman named, Chhatrapati Shivaji Maharaj, who led the genesis of Maratha empire. The reason I choose the tool to name Shivneri is that, it signifies birth, and I consider this tool as the birth of my distributed systems work. Also, I learnt the art of naming the tool as something meaningful from this repository: [Raigad](https://github.com/Netflix/Raigad), which is Netflix's OSS.

### Motivation 
This project sources its inspiration from 

### Design Considerations 

### Features 

##### Author
Ashwin Tumma

Computer Science Graduate Student,

Stony Brook University, New York, United States
