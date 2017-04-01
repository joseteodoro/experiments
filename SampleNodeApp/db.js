import fs from 'fs';
import path from 'path';
let db = null;
let sequelize;
module.exports = function app () {
    if (!db) {
        const config = require('./libs/config');
        const Sequelize = require('sequelize');
        sequelize = new Sequelize(config.database, config.username, config.password, config.params);
        db = {
            sequelize,
            Sequelize,
            models: {}
        };
    }
    const dir = path.join(__dirname, 'models');
    fs.readdirSync(dir).forEach(file => {
        const modelDir = path.join(dir, file);
        const model = sequelize.import(modelDir);
        db.models[model.name] = model;
    });
    Object.keys(db.models).forEach(key => {
        db.models[key].associate(db.models);
    });
    return db;
};
