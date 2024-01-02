const amqp = require('amqplib')

const queue = "THEATER"

amqp.connect({
    host: "localhost",
    port: 5672,
    username: "admin",
    password: "admin"
}).then((connection)=> {
    connection.createChannel().then((channel)=> channel.consume(queue, (message)=>{
        console.log(message.content.toString())
    }, {noAck: true}))
}).catch((erro) => console.log(erro))