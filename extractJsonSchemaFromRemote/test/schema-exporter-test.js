'use strict';

let assert = require('assert');
let SchemaExporter = require('../schema-exporter');

describe('schema-exporter', function () {
  it('should export to file', function () {
    let fakeFile = './schema.json';
    let fileContent = {};
    let fakeConfig = {config: 'fake'};
    let configGot = {};
    let fakeSchema = {schema: 'fake schema'};
    let fileGot = {};
    let customXtractor = function (config, extractor) {
      configGot = config;
      extractor(null, fakeSchema);
    };

    let customFS = {
      writeFileSync: function (file, content) {
        fileContent = content;
        fileGot = file;
      }
    };

    let ee = new SchemaExporter(customXtractor, customFS);
    ee.export_to_file(fakeConfig, fakeFile);
    console.log(require('util').inspect(configGot));

    assert.deepEqual(fakeConfig, configGot);
    assert.deepEqual(JSON.stringify(fakeSchema, null, 4), fileContent);
    assert.deepEqual(fakeFile, fileGot);
  });
});
