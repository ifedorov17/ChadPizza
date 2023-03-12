import React, {Component} from 'react';
import CRUDService from "../Services/CRUDService";
import AddOrderModalComponent from "../Functions/AddOrderModal";
import UpdateOrderModal from "../Functions/UpdateOrder";
class ListOrderComponent extends Component {
    constructor(props){
        super(props)

        this.state = {
                orders : [],
                isAddOrderActive : false,
                isUpdateOrderActive : false,
                shouldPageRefresh : false


        }
    /*    this.addOrder = this.addOrder.bind(this);
        this.editOrder = this.editOrder.bind(this);
        this.delOrder = this.delOrder.bind(this);*/
        this.setAddOrderActive = this.setAddOrderActive.bind(this);

    }
    setAddOrderActive = () => {
        this.setState({isAddOrderActive : true})
    }
    delOrder(id){
        CRUDService.deleteOrder(id).then( res => {
            this.setState({orders: this.state.orders.filter(orders => orders.id !== id)});
        });
    }
    viewOrder(id){
        this.props.history.push(`/view-order/${id}`);
    }
    editOrder(id){
        this.props.history.push(`/add-order/${id}`);
    }

    componentDidMount(){
        CRUDService.getOrders().then((res) => {
            this.setState({ orders: res.data})
            console.log(res.data)
        });

    }



    addOrder(){
        console.log(this.props.history + "/add-order")
        this.props.router.push('/add-order');
    }
    render() {
        return (
            <div className = "container-fluid">
                <h2 className="text-center">Orders</h2>
                <div className = "row-cols-2 gap-2" >
                    <AddOrderModalComponent/>
                    <button className="btn btn-success"
                            >Refresh Table</button>
                </div>
                <br></br>
                <div className = "row">
                    <table className = "table table-striped table-bordered">

                        <thead>
                        <tr>
                            <th> Client ID</th>
                            <th> Order Date/Time</th>
                            <th> Order Status</th>
                            <th> Order Summary(RUB) </th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.orders.map(
                                order =>
                                    <tr key = {order.userID}>
                                        <td> {order.userID} </td>
                                        <td> {order.orderDateTime.toString()}</td>
                                        <td> {order.status}</td>
                                        <td> {order.totalPrice}</td>
                                        <td>
                                            <UpdateOrderModal clientID = {order.userID}
                                                              orderStatus = {order.status}
                                                              orderSummary = {order.totalPrice}/>
                                        </td>
                                    </tr>
                            )
                        }
                        </tbody>
                    </table>

                </div>

            </div>
        )
    }
}

export default ListOrderComponent;
