import React from 'react';
import { Form, Button } from 'react-bootstrap'
import { render } from 'react-dom';
import Select from 'react-select'


class OrderForm extends React.Component {

    
    
    sides = [ 
        { value: "BUY", label: "BUY"},
        { value: "SELL", label: "SELL"}
    ];
    symbols = [
        { value: "1", label: "AMZN"}
            ]
    owners = [
        { value: "1", label: "CP1"},
        { value: "2", label: "CP2"}
    ];

    

    render(){
        let {orderData, handleChange,handleOrderFormSubmit,handleChangeNumber} = this.props;
        console.log(orderData.side);
        return (
            <Form onSubmit={handleOrderFormSubmit}>
                <Form.Group controlId="orderSide" >
                    <Form.Label>Side: </Form.Label>
                    {/* <ComboBox controlId="sideComboBox" data={this.sides}/> */}
                    <Select options={this.sides} value={orderData.side} onChange={(evt) => this.props.handleChange("side",evt)} />
                    
                </Form.Group>
                <Form.Group controlId="orderSymbol">
                    <Form.Label>Symbol: </Form.Label>
                    {/* <ComboBox controlId = "symbolComboBox" data={this.symbols}/> */}
                    <Select options={this.symbols} name="symbol" value={orderData.symbol} onChange={(evt) => this.props.handleChange("symbol",evt)}/>
                </Form.Group>
                <Form.Group controlId = "orderOwner">
                    <Form.Label >Owner: </Form.Label>
                    {/* <ComboBox controlId = "ownerComboBox" data={this.owners}/> */}
                    <Select options={this.owners} name="owner"  value={orderData.owner} onChange={(evt) => this.props.handleChange("owner",evt)}/>
                </Form.Group>
                <Form.Group controlId = "orderQuantity">
                    <Form.Label>Quantity: </Form.Label>
                    <Form.Group>
                        <input type="number" id="stepperQuantity" name="quantity" step="1" value={orderData.quantity} onChange={handleChangeNumber}></input>
                    </Form.Group>
                </Form.Group>
                <Form.Group controlId = "orderPrice">
                    <Form.Label>Price: </Form.Label>
                    <Form.Group>
                        <input type="number" id="stepperPrice" name="price" step="0.01" value={orderData.price} onChange={handleChangeNumber}></input>
                    </Form.Group>
                    
                </Form.Group>
                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>

        )
    }
}

export default OrderForm
