'use strict';

function SchemaExporter (customXtractor, customFS) {
  this.mysqlspec = customXtractor || require('mysqlspec');
  this.fs = customFS || require('fs');
}

SchemaExporter.prototype.extract = function (config, cb) {
  let extractor = function extractor (err, schema) {
    if (err) {
      cb(err, null);
    } else {
      cb(null, schema);
    }
  };
  this.mysqlspec(config, extractor);
};

SchemaExporter.prototype.export_to_file = function (config, file) {
  let _this = this;
  this.extract(config, function (err, schema) {
    if (err) {
      console.log('Error: ' + require('util').inspect(err));
    } else {
      _this.fs.writeFileSync(file, JSON.stringify(schema, null, 4));
      console.log('write done!');
    }
  });
};

module.exports = SchemaExporter;
