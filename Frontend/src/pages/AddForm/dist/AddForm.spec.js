"use strict";
exports.__esModule = true;
var testUtils_1 = require("../../utils/testUtils");
var AddForm_1 = require("./AddForm");
exports["default"] = describe("Form", function () {
    it("Renders", function () {
        var container = testUtils_1.customRender(React.createElement(AddForm_1["default"], null)).container;
        expect(container).toMatchSnapshot();
    });
});
