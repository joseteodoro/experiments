
const assert = require('assert')

describe.only('decomposition suite', function () {
  it('decompose fuction return', function (done) {
    const obj = {
      fn : function () {
        const name = 'some name'
        const age = 25
        return {name, age}
      }
    }
    const {name, age} = obj.fn()
    assert.equal(name, 'some name')
    assert.equal(age, 25)
    done()
  })
  it('decompose fuction return with error', function (done) {
    const obj = {
      fn : function (message) {
        if (message) {
          return {error: message, result: null}
        }
        return {result: 'my result'}
      }
    }
    const {err, result} = obj.fn()
    assert.equal(result, 'my result')
    assert.equal(err, null)
    done()
  })
})
