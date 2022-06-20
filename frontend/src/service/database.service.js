import api from './api';

const getCNAB = () => {
    return api.get('/cnab');
}

const saveCNAB = cnab => {
    return api.post('/cnab', cnab);
}

const databaseService = {
    getCNAB,
    saveCNAB,
}

export default databaseService;