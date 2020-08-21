import React, { Component } from 'react';

import BuySide from "./BuySide"
import SellSide from "./SellSide"
import { Container, Row, Col } from 'react-bootstrap'

class OrderBook extends Component {

    render() {
        return (
            <Container>
                <Row>
                    <Col sm={6}>
                        <BuySide orders={this.props.buyOrders} />
                    </Col>
                    <Col sm={6}>
                        <SellSide orders={this.props.sellOrders} />
                    </Col>
                </Row>
            </Container>
        )
    }
}

export default OrderBook