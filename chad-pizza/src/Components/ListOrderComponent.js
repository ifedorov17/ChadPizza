import React, {Component} from 'react';
import CRUDService from "../Services/CRUDService";
import AddOrderButton from '../RouteSettings/AddOrderButton'
import BackButton from "../RouteSettings/AddOrderButton";
import UpdateOrderButton from "../RouteSettings/UpdateOrderButton";
class ListOrderComponent extends Component {
    constructor(props){
        super(props)

        this.state = {
                orders : []
        }
        this.addOrder = this.addOrder.bind(this);
        this.editOrder = this.editOrder.bind(this);
        this.delOrder = this.delOrder.bind(this);
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
            console.log("HERE COMES JOHN CENA " + res);;
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
                <div className = "row">
                    {/*<button className="btn btn-primary"
                            onClick={this.addOrder}> Add Order</button>*/}
                    <AddOrderButton/>.
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
                                        <td> {order.orderDateTime}</td>
                                        <td> {order.status}</td>
                                        <td> {order.totalPrice}</td>
                                        <td>
                                          {/*  <button onClick={ () => this.editOrder(order.id)}
                                            className="btn btn-info">Update </button>
                                            <button style={{marginLeft: "10px"}}
                                                    onClick={ () => this.delOrder(order.id)}
                                                    className="btn btn-danger"> Delete </button>
                                            <button style={{marginLeft: "10px"}}
                                                    onClick={ () => this.viewOrder(order.id)}
                                                    className="btn btn-info"> View </button>*/}
                                            <UpdateOrderButton/>
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
