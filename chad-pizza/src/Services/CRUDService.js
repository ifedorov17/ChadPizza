import axios from 'axios';
import OrderTableViewComponent from "../Components/OrderTableViewComponent";

const API_BASE_URL = "http://localhost:8080/order"

class CRUDService {

    //Get all orders
    getOrders(){
        return axios.get(API_BASE_URL);
    }

    //Add new order
    addOrder(order){
        return axios.post(API_BASE_URL, order);
    }

    //Get order by ID
    getOrderById(orderID){
        return axios.get(API_BASE_URL + '/' + orderID);
    }

    //Update order by ID
    updateOrder(order, orderID){
        return axios.put(API_BASE_URL + '/' + orderID, order);
    }

    //Delete order by ID
    deleteOrder(orderID){
        return axios.delete(API_BASE_URL + '/' + orderID);
    }
}
export default new CRUDService;