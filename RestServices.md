POST : http://localhost:8080/expenses/expenses
{

"username":"varun",
"amount":100.0,
"date":25022022,
"description":"Food"

}

POST : http://localhost:8080/friends/addFriend

{
"username": "vansh",
"name": "vansh",
"lastname": "sawaji",
"balance": 300.0,
"expenses": [
{
"amount": 500.0,
"date": 20222702,
"payer_id":1,
"description": "Food"
},
{
"amount": 300.0,
"date": 20222602,
"payer_id":2,
"description": "Travel"
}
]
}
GET : http://localhost:8080/expenses/getall

GET : http://localhost:8080/friends/getBalance

