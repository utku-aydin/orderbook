import React from "react";
import { Table, Button } from "react-bootstrap";


const SellSideHeader = () => {
    return (
        <tr>
            <th>price</th>
            <th>quantity</th>
            <th>total</th>
            <th>Symbol</th>
            <th>Cancel</th>
        </tr>
    );
}

const SellSideRow = ({ order }) => {

    return (<tr>
        <td>{order.price}</td>
        <td>{order.quantity}</td>
        <td>{order.price * order.quantity}</td>
        <td>{order.symbol}</td>
        <td><Button>Cancel</Button></td>
    </tr>
    );
}

class SellSide extends React.Component {

    static defaultProps = {
        orders: [
            {
                "price": 1.01,
                "quantity": 100,
                "symbol": "AMZN"

            },
            {
                "price": 1.04,
                "quantity": 110,
                "symbol": "AMZN"

            },
            {
                "price": 1.07,
                "quantity": 120,
                "symbol": "AMZN"

            }
        ]
    }


    render() {
        // Here is a debug method to moniter incoming contact data
        console.log("Rending Order Book:")
        console.log(this.props.orders)


        return (
            <Table striped bordered hover>
                <thead>
                    <SellSideHeader/>
                </thead>
                <tbody>
                    {this.props.orders.map((order, i) => {
                        return <SellSideRow order={order} key={i} />
                    })}

                </tbody>
            </Table>)
    }
}

export default SellSide