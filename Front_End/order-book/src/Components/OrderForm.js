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
        { value: "AMZN", label: "AMZN"}
            ]
    owners = [
        { value: "CP1", label: "CP1"},
        { value: "CP2", label: "CP2"}
    ];
    

    // constructor(props){
    //     super(props);
    //     this.handleOrderChange = this.handleOrderChange.bind(this);
    //     this.handleSubmit = this.handleSubmit.bind(this);
    // }

    // handleOrderChange(event){
    //     let name = event.target.name;
    //     let value = event.target.value;

    //     this.setState(
    //         prevState => {
    //             return {newOrder : {... prevState.newOrder, [name]: value}}
    //         },
    //         () => 
    //             console.log('changed' + name + "to: " + this.state.newOrder[name])
    //     );
    // }

    // handleSubmit(event) {
    //     if (event) event.preventDefault();
    //     const { newOrder } = this.state;
    //     alert(JSON.stringify(submi))
    // }
    

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
                    <Form.Group>
                        <input type="number" id="stepperQuantity" name="stepperQuantity" step="1"></input>
                    </Form.Group>
                </Form.Group>
                <Form.Group controlId = "orderPrice">
                    <Form.Label>Price: </Form.Label>
                    <Form.Group>
                        <input type="number" id="stepperPrice" name="stepperPrice" step="0.01"></input>
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
