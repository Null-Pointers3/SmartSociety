var express = require('express');
var bodyParser = require('body-parser');
const data = require(__dirname + '/payment.json');

var app = express();
app.get('/electricity/:id', function (req, res) {
    console.log('request made at' + req.url);
    res.json(data);
});
app.listen(3000, '172.16.10.160');
