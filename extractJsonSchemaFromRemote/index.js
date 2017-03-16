'use strict';

let SchemaExporter = require('./schema-exporter');
let mysql = require('mysql');

let config = {
  user: 'user',
  password: 'pass',
  host: 'host',
  database: 'db'
};

let connection = mysql.createConnection(config);
connection.connect(function (err) {
  if (err) {
    console.error('error connecting: ' + err.stack);
    return;
  }

  console.log('connected as id ' + connection.threadId);
  connection.end();
});

let file = './schema.json';
let ee = new SchemaExporter();
ee.export_to_file(config, file);
console.log('done!');
