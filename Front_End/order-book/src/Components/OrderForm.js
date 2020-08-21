import React from 'react';
import { Form, Button } from 'react-bootstrap'
import { render } from 'react-dom';
import Select from 'react-select'


class OrderForm extends React.Component {
    sides = [ 
        { value: "BUY", label: "BUY"},
        { value: "SELL", label: "SELL"}
    ];
    symbols = ["XLON"];
    owners = ["CP1", "CP2"]

    render(){
        return (
            <Form>
                <Form.Group controlId="orderSide">
                    <Form.Label>Side: </Form.Label>
                    {/* <ComboBox controlId="sideComboBox" data={this.sides}/> */}
                    <Select options={this.sides} />
                    
                </Form.Group>
                <Form.Group controlId="orderSymbol">
                    <Form.Label>Symbol: </Form.Label>
                    {/* <ComboBox controlId = "symbolComboBox" data={this.symbols}/> */}
                    <Select options={this.symbols} />
                </Form.Group>
                <Form.Group controlId = "orderOwner">
                    <Form.Label >Owner: </Form.Label>
                    {/* <ComboBox controlId = "ownerComboBox" data={this.owners}/> */}
                    <Select options={this.owners} />
                </Form.Group>
                <Form.Group controlId = "orderQuantity">
                    <Form.Label>Quantity: </Form.Label>
                    <input type="number" id="stepperQuantity" name="stepperQuantity" step="1"></input>
                </Form.Group>
            </Form>

        )
    }
}

export default OrderForm
