# Payslip Exercise

### Requirements

Create a service to calculate pay, produce a payslip and send it for payment

1. Calculate base pay and overtime for employees 
   1. Employees are paid their normal hourly rate up to their weekly limit
   2. Hours outside normal rate get an additional premium as overtime 
2. An employee can have different roles
   1. Engineer, £5 per hour, 35% premium for overtime
   2. Manager, £6 per hour, 25% premium for overtime
   3. Director, £7 per hour, not allowed to do overtime
3. There is a max number of paid hours of 60 and some roles are not allowed to do overtime
4. Use the provided printing service to create the payslip
5. Use the provided payment service to request payment

e.g.

An engineer on a 20-hour contract working 30 hours would make: 
   £100 base
   £50 x 35% overtime £67.50
   Total: £167.50
