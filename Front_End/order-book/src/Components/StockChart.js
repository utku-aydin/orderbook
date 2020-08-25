import React from 'react';
import { render } from 'react-dom';
import axios from 'axios';
import 'zingchart/es6';
import ZingChart from 'zingchart-react';
// EXPLICITLY IMPORT MODULE from node_modules
import "zingchart/modules-es6/zingchart-maps.min.js";
import "zingchart/modules-es6/zingchart-maps-usa.min.js";

class StockChart extends React.Component {
    constructor(props) {
      super(props);
      this.state = {
        orders: [],
        config:{
            type: 'line',
            series: [{
              values: [21,
                21,
                21,
                24,
                22]
            }]
        }
          
      }
    }
    render() {
      console.log("Rending Graph:")
      console.log(this.props.orders)
      return (
        
        <div>
          <ZingChart data={this.state.config}/>
        </div>
      );
    }
  }

  export default StockChart