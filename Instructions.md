# How to run the program on Aviary

<b>
Important Note:
</b>
<br>
<em>
-Our program takes 15 minutes to populate in WSL and 45 minutes in IntelliJ IDE
<br>
-You can choose to use the already populated database or populate an empty database
<br>
-In Case you choose to use your own empty database to populate, you can put your credentials in 'authUnpopulated.cfg' and then run the program OR you can use the already saved  credentials in 'authUnpopulated.cfg' if you want to populate it using our credentials
</em>

<br>

## Step 1
Use the command 'make run' to compile and run the program.


## Step 2
After running the program you will get this prompt:

```
Do you want to populate the database again or want to use already populated database?
Press 'y' to populate the database again and 'n' to use already populated database:
```
At the prompt above:
-Enter 'y' to populate an empty database with the credentials saved in authUnpopulated.cfg
-Enter 'n' to use the already populated database with the credentials saved in authPopulated.cfg


## Step 3
Follow the TUI for further instructions 

<br>
<br>

# How to use the TUI on Aviary
All the queries have been categorized to make it easy for the marker/professor/analyst to navigate.

## Step 1
The next prompt asks for a Category number

```
Select one of the following Categories:
1) Users [Contains 2 Queries]
2) Videos [Contains 7 Queries]
3) Channels [Contains 6 Queries]
4) Advertisements [Contains 2 Queries]
5) Companies [Contains 2 Queries]
6) Countries [Contains 2 Queries]
7) To Quit the Program
Enter Number:
```
Enter the catergory number (1-7) 

## Step 2
The next prompt will ask for Query number (I am showing the queries for Users) 
```
Select one of the following Queries for Users:
1) Get all the users.
2) Get users that don't own a channel.
3) To go back to Categories.
Enter Number:
```
Enter the Query number and then it will give you the View for it.
