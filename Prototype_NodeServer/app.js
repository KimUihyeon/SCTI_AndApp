var express  = require('express');
var nunjucks  = require('nunjucks');
var app =  express();

app.use(express.static('/public'));

var env = nunjucks.configure(['views/'], { // set folders with templates
    autoescape: true, 
    express: app
});

app.get( '/' , function(req, res){
    res.render('index.html', null);;
})




app.listen(3000, function () {
    console.log('Example app listening on port 3000!');
  });