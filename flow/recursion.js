'use strict';

function Calc () {}

Calc.prototype.factorial = function (value, output) {
  let err = function err (msg) {
    console.log(`Error: ${msg}`);
  };
  let entry = {
    'value': value,
    'factorial': 1
  };
  let end = function (_err, _value, next) {
    if (value.value === 1) {
      this._factorial(value, err, output);
    }
  };
  this._factorial(entry, err, this._factorial, end);
};

Calc.prototype._factorial = function (value, err, tail, end) {
  value.factorial *= value.value--;
  this._factorial(value, err, tail, end);
};

module.exports = Calc;
