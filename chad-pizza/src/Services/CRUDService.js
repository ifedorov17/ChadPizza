import axios from 'axios';

const API_BASE_URL = "http://localhost:8080"

class CRUDService {

    //Get all orders
    getOrders(){
        return axios.get(API_BASE_URL + "/order");
    }
    getCustomers(){
        return axios.get(API_BASE_URL + "/customer");
    }
    addNewCustomer (customer) {
        return axios.post(API_BASE_URL + "/customer", customer);
    }

    //Add new order
    addBlankOrder(userID){
        return axios.post(API_BASE_URL + "/order", userID);
    }
    //update order positions
    updateOrder(positions) {
        return axios.put(API_BASE_URL + "/order/position", positions)
    }
    updateOrderStatus(status) {
        return axios.put(API_BASE_URL + "/order/status", status)
    }


    //Delete order by ID
    deleteOrder(orderID){
        return axios.delete(API_BASE_URL + '/' + orderID);
    }
}
export default new CRUDService;