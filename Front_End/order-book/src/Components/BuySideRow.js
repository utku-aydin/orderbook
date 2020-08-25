import React from "react";
import { Table, Button } from "react-bootstrap";
import OrderAdjuster from "./OrderAdjuster";
import styled from 'styled-components';

const RedText = styled.p`
    color: #ed1212;
`

const GreenText = styled.p`
    color: #008000;
`

class BuySideRow extends React.Component {

    state = {
        editOrder: {
            quantity: "",
            price: ""
        }
    }



    constructor(props) {
        super(props);
        this.state.editOrder.quantity = this.props.order.order_size - this.props.order.number_matched;
        this.state.editOrder.price = this.props.order.price;
        this.state.editOrder.total = this.props.order.price * this.state.editOrder.quantity;
    }

    addOne = (name,event) =>{
        console.log(name);
        


    }

    handleChangeNumber = (name,value,event) => {
        let inputName = name
        let inputValue = value
        if(event.shiftKey){
            inputValue = value *5;
        }
    
        let orderData = this.state.editOrder;
    
        if (orderData.hasOwnProperty(inputName)) {
            console.log(orderData[inputName])
            console.log(inputValue)
          orderData[inputName] += inputValue;
         
          this.setState({ newOrderData: orderData })
        }
      }


    render(){
        let {price,order_size,number_matched,side,stock} = this.props.order;
        let quantity = order_size - number_matched;
        let total = price * quantity;
        let sum = this.state.editOrder.price * this.state.editOrder.quantity
        
        let editTotal = (Math.round(sum * 100) / 100).toFixed(2);
        
    
        return (<tr>
            <td>
                <Button>Cancel</Button>
                {this.state.editOrder.quantity != quantity || this.state.editOrder.price !=price ? <React.Fragment> <Button onClick={this.updateOrder}>Update</Button>
                <Button onClick={this.resetEdit}>Reset</Button></React.Fragment> : null}
                </td>
            <td>{stock.stock_symbol}</td>
            <td>{total}{" "}
         {editTotal > total ? <GreenText>{editTotal}</GreenText> : null}
         {editTotal < total ? <RedText>{editTotal}</RedText> : null}
         </td>
            <td><OrderAdjuster value={quantity}
             editValue={this.state.editOrder.quantity}
             handleChangeNumber = {this.handleChangeNumber}
             name={"quantity"}
             tickSize={1}/></td>
            <td><OrderAdjuster value={price}
             editValue={this.state.editOrder.price}
             handleChangeNumber = {this.handleChangeNumber}
             name={"price"}
             tickSize={0.01}/></td>
    
        </tr>
        );  
    }

}

export default BuySideRow