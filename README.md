# HKUST ISOM3320 Project Case
 ![hkust](https://hkust.edu.hk/sites/default/files/images/HKUST-original_0.svg)
## Introduction
Recently founded by a group of HKUST graduate, __ReReReThink__ is a newly established software house. The software house is specialized in building software tools for business process re-engineering and office automation in Hong Kong. Your team has been hired as IT consultants to develop an application system for a cleaning company to manage its operations.

## Requirements
***Ever-Clean: Background and Business Process***

Ever-Clean is a cleaning company providing home cleaning service in Hong Kong. After receiving the appointment from a client, Ever-Clean will dispatch the cleaner to the client's home to provide the cleaning service. The existing operations management system of Ever-Clean is incapable of meeting the business needs of the company. To replace the existing incapable system, Ever-Clean hired your team to develop a new operations management system.
After conducting feasibility analysis and estimating the time frame of building such system, the project is designed to build in two phases: (1) a local system for managing the staff and appointment, and (2) an online booking system.
Due to the limited time constraint, your task is to complete phase 1 of the project development only. Here are some points about the operation you need to know:
1. There are two types of staff: team manager and cleaner. A team manager manages a team of cleaners and the appointments of his/her team. A cleaner can check his/her assigned appointments and mark the appointments as done through the system when the appointments are finished.
2. The salary of the cleaner is calculated as follow: the hourly rate of the cleaner * total working hour.
3. There are multiple teams in the company. Each team is managed by exactly one team manager. Each team manager can only manage one team.
4. The team manager will manually calculate the total amount of payments to be paid by the clients, when the clients make the appointments.
5. Each appointment will be handled by one cleaner only.
6. After finishing an appointment, the cleaner will have 30 minutes for having a rest or going to next client's home. They should not have an appointment during this period. This period is not counted as working hour.

## Summarized Basic Functional Requirements for Phase 1
***Process Requirement***

Note: Staff need to login into the system before using functions s1 to s6
1. Allow team manager to maintain (view, add, modify and remove) cleaner data in their team.
2. Allow team manager to maintain (view, add, modify and remove) appointment for their team.
3. Allow cleaner to check their appointments in the coming 7 days (including today).
4. Allow the cleaner to mark the appointments as done when the appointments are finished.
5. Allow the team manager to generate a cleaner salary report in the current month.
6. Allow all staff (team manager and cleaner) to check Top-3 cleaner in the company (By Revenue).

***Data Requirement***

Note: You may keep all the data in csv file(s)
1. Appointment details (e.g., staff id, client’s name, client’s address, date, time, duration, amount etc.)
2. Staff details (e.g., staff id, name, password, salary etc.)
3. Any other data that you think will be valuable.

***Remarks***

1. You can assume that the customer make an appointment by calling a hotline and team manager will add the appointment to the system.
2. You can assume that team manager accounts are already created and you can add them to CSV directly.
3. You can assume that the assigned team of the staff is fixed after the cleaner’s record is created in the system.
4. Other functions e.g. Accounting, Finance and Inventory are not included in the system.
5. For simplicity, we keep all data in English only.
