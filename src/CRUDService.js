import axios from 'axios';

const API_BASE_URL = "http://localhost:8080"

class CRUDService {

    //Get all orders
    getAllPizzas(){
        return axios.get(API_BASE_URL + "/pizza");
    }

    postOrder(order) {
        return axios.post(API_BASE_URL + "/order", order);
    }

}
export default new CRUDService;