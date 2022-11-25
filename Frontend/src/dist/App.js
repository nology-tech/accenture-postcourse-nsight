"use strict";
exports.__esModule = true;
var react_router_dom_1 = require("react-router-dom");
var AddForm_1 = require("./pages/AddForm/AddForm");
var Home_1 = require("./pages/Home/Home");
var NotFound_1 = require("./pages/NotFound/NotFound");
var User_1 = require("./pages/User/User");
var Users_1 = require("./pages/Users/Users");
require("./styles/main.scss");
var App = function () {
    return (React.createElement(react_router_dom_1.Routes, null,
        React.createElement(react_router_dom_1.Route, { path: "/home", element: React.createElement(Home_1["default"], null) }),
        React.createElement(react_router_dom_1.Route, { path: "/users", element: React.createElement(Users_1["default"], null) }),
        React.createElement(react_router_dom_1.Route, { path: "/users/:userId", element: React.createElement(User_1["default"], null) }),
        React.createElement(react_router_dom_1.Route, { path: "/form", element: React.createElement(AddForm_1["default"], null) }),
        React.createElement(react_router_dom_1.Route, { path: "/", element: React.createElement(react_router_dom_1.Navigate, { replace: true, to: "/home" }) }),
        React.createElement(react_router_dom_1.Route, { path: "*", element: React.createElement(NotFound_1["default"], null) })));
};
exports["default"] = App;
