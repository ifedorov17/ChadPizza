import React, {Component} from 'react'
import CRUDService from "../Services/CRUDService";
class OrderTableViewComponent extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            id: this.props.match.params.id,
            order: {}
        }

    }
    componentDidMount() {
        CRUDService.getOrderById(this.state.id).then(
            res => {
                this.setState({order : res.data});
            })
    }

    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center"> View Employee Details</h3>
                    <div className = "card-body">
                        <div className = "row">
                            <label> Client ID   </label>
                            <div> { this.state.order.ID }</div>
                        </div>
                        <div className = "row">
                            <label> Date and Time </label>
                            <div> { this.state.order.Date }</div>
                        </div>
                        <div className = "row">
                            <label> Order Status </label>
                            <div> { this.state.order.Status }</div>
                        </div>
                        <div className = "row">
                            <label> Order Summary(RUB)  </label>
                            <div> { this.state.order.Summary }</div>
                        </div>
                    </div>

                </div>
            </div>
        )
    }

}

export default OrderTableViewComponent;
