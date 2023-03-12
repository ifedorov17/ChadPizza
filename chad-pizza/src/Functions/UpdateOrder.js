import React, {Component, useState} from 'react';
import './modal.css'
import CRUDService from "../Services/CRUDService";


class UpdateOrderModal extends Component {
    constructor(props) {
        super(props)
        this.state = {
            clientID : '',
            orderStatus: '',
            orderSummary:'',
        }
        this.changeOrderStatusHandler = this.changeOrderStatusHandler.bind(this)
        this.changeClientIDHandler = this.changeClientIDHandler.bind(this)
        this.changeSummaryHandler = this.changeSummaryHandler.bind(this)
        this.onSubmitHandler = this.onSubmitHandler.bind()
        this.setID = this.setID.bind(this)
        this.updateButtonAction = this.updateButtonAction.bind(this)
    }

    changeClientIDHandler = (e) => {
        this.setState({clientID: e.target.value})
        console.log(e.target.value)
    }
    changeOrderStatusHandler = (e) => {
        e.preventDefault();
        this.setState({orderStatus : e.target.value})
        console.log(e.target.value)
    }
    changeSummaryHandler = (e) => {
        this.setState({orderSummary : e.target.value})
        console.log(e.target.value)
    }
    onSubmitHandler = (e) => {
        e.preventDefault();
        let status = { id: 1,
            status: this.state.orderStatus};
        console.log("order to PUT" + JSON.stringify(status));
        CRUDService.updateOrderStatus(status);
    }

    setID(ID){
        this.setState({clientID : ID})
     }

     updateButtonAction() {
        this.setState({ clientID : this.props.clientID,
            orderStatus : this.props.orderStatus,
            orderSummary : this.props.orderSummary,
        })
         console.log("Set to state" + this.state.clientID + this.state.orderStatus + this.state.orderSummary)
     }

    render() {
        return (
            <>
                <button type="button"
                        class="btn btn-primary"
                        data-bs-toggle="modal"
                        data-bs-target="#Update"
                        onClick = {this.updateButtonAction}
                >
                    Update
                </button>

                <div class="modal" id="Update" tabIndex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title"  className = "text-bg-warning pe-auto fs-1" id="UpdateModel" >Update Order</h5>
                            </div>
                            <div class="modal-body" className="container-md">
                                <div className="mb-3">
                                    <label htmlFor="ClientIdInputLabel" className="form-label">Client ID</label>
                                    <input type="text" className="form-control"
                                           id="ClientIDInput"
                                           defaultValue={this.props.clientID}
                                           onChange = {this.changeClientIDHandler} />
                                </div>
                                <label htmlFor="SelectLabel" className="form-label">Order Status</label>
                                <select className="form-select form-select-lg mb-3"
                                        aria-label="Order-Status-Select"
                                        defaultValue={this.props.orderStatus}
                                        onChange={this.changeOrderStatusHandler}>
                                    <option value="PAID">PAID</option>
                                    <option value="DRAFT">DRAFT</option>
                                    <option value="CANCELLED">CANCELLED</option>
                                </select>
                                <div className="mb-13">
                                    <label htmlFor="SummaryInputLabel" className="form-label">OrderSummary(RUB)</label>
                                    <input type="text"
                                           className="form-control"
                                           id="SummaryInput"
                                           defaultValue={this.props.orderSummary}
                                           onChange={this.changeSummaryHandler}/>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
                                <button type="button"
                                        class="btn btn-primary"
                                        data-bs-dismiss="modal"
                                        onClick={this.onSubmitHandler}>Submit</button>
                            </div>
                        </div>
                    </div>
                </div>
            </>
        );

    }

}


export default UpdateOrderModal;

