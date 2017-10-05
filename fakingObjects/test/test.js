

// db = {
//   query : function (id) {
//     this.connect...
//     this.find...
//     return found;
//   }
// }

const assert = require('assert')

function Checker (db) {
  this.db_ref = db

  return this
}

Checker.prototype.doSomething = function doSomething (param) {
  return this.db_ref.query(param)
}

describe('test suite', function () {
  it('test false', function (done) {
    let fake = {
      query : function (id) { return false }
    }
    let checker = new Checker(fake)
    done(assert(true, checker.doSomething('banana')))
  })
  it('test true', function (done) {
    let fake = {
      query : function (id) { return true }
    }
    let checker = new Checker(fake)
    done(assert(true, checker.doSomething('batata')))
  })
})
