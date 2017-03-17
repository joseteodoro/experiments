'use strict';

let assert = require('assert');
let Calc = require('../recursion');

describe('let it flow', function () {
  it('flow recursion with 1', function (done) {
    let calc = new Calc();
    calc.factorial(1, function (value) {
      assert.equal(1, value);
    });
  });
  it('flow recursion with 2', function (done) {
    let calc = new Calc();
    calc.factorial(2, function (value) {
      assert.equal(2, value);
    });
  });
  it('flow recursion with 3', function (done) {
    let calc = new Calc();
    calc.factorial(3, function (value) {
      assert.equal(6, value);
    });
  });
});
