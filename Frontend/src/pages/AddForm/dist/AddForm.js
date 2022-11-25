"use strict";
exports.__esModule = true;
exports.AddForm = void 0;
require("./AddForm.css");
var formik_1 = require("formik");
var yup = require("yup");
// interface MyFormValues {
//   firstName: string;
//   lastName: string;
//   dateOfBirth: Date;
//   email: string;
//   mobileNumber: string;
//   dateOfGraduation: Date;
//   employed: false;
//   employer: string;
// }
var personSchema = yup.object({
    firstName: yup
        .string()
        .max(15, "Must be 15 characters or less")
        .required("First name required"),
    lastName: yup
        .string()
        .max(20, "Must be 20 characters or less")
        .required("Last name required"),
    dateOfBirth: yup
        .date()
        .typeError("The value must be a date (YYYY-MM-DD)")
        .required("Date of birth required"),
    email: yup
        .string()
        .email("Invalid email address")
        .required("E-mail required"),
    mobileNumber: yup
        .string()
        .typeError("That doesn't look like a mobile number")
        // .positive("That doesn't look like a mobile number")
        // .integer("A phone number can't include a decimal point")
        .required("A mobile number required"),
    dateOfGraduation: yup
        .date()
        .typeError("The value must be a date (YYYY-MM-DD)")
        .required("Date of graduation required"),
    employed: yup.boolean(),
    employer: yup.string().max(20, "Must be 20 characters or less"),
    role: yup
        .string()
        .oneOf(["designer", "development", "product", "other"], "Invalid Job Role")
});
exports.AddForm = function () {
    var initialValues = {
        firstName: "",
        lastName: "",
        dateOfBirth: new Date("1995-12-17T03:24:00"),
        email: "",
        mobileNumber: "",
        dateOfGraduation: new Date("1995-12-17T03:24:00"),
        employed: false,
        employer: "",
        role: ""
    };
    return (React.createElement("div", { className: "add-student-form" },
        React.createElement("header", null,
            React.createElement("h1", null, "Add Student")),
        React.createElement(formik_1.Formik, { initialValues: initialValues, onSubmit: function (values, actions) {
                console.log({ values: values, actions: actions });
                alert(JSON.stringify(values, null, 2));
                actions.setSubmitting(false);
            } },
            React.createElement(formik_1.Form, null,
                React.createElement("section", { className: "add-student-form__section" },
                    React.createElement("div", { className: "add-student-form__section__grid" },
                        React.createElement("div", null,
                            React.createElement("label", { htmlFor: "firstName" }, "First Name"),
                            React.createElement(formik_1.Field, { id: "firstName", name: "firstName", type: "text" }),
                            React.createElement("label", { htmlFor: "lastName" }, "First Name"),
                            React.createElement(formik_1.Field, { id: "lastName", name: "lastName", type: "text" }),
                            React.createElement("label", { htmlFor: "dateOfBirth" }, "Date of Birth"),
                            React.createElement(formik_1.Field, { id: "dateOfBirth", name: "dateOfBirth", type: "date" }),
                            React.createElement("label", { htmlFor: "email" }, "E-mail"),
                            React.createElement(formik_1.Field, { id: "email", name: "email", type: "text" }),
                            React.createElement("label", { htmlFor: "mobileNumber" }, "Mobile number"),
                            React.createElement(formik_1.Field, { id: "mobileNumber", name: "mobileNumber", type: "text" }),
                            React.createElement("label", { htmlFor: "dateOfGraduation" }, "Graduation date"),
                            React.createElement(formik_1.Field, { id: "dateOfGraduation", name: "dateOfGraduation", type: "date" })),
                        React.createElement("div", null,
                            React.createElement("div", null,
                                React.createElement("label", { htmlFor: "employed" }, "Employed"),
                                React.createElement(formik_1.Field, { id: "employed", name: "employed", type: "checkbox" })),
                            React.createElement("div", null,
                                React.createElement("label", { htmlFor: "employer" }, "Employer"),
                                React.createElement(formik_1.Field, { id: "employer", name: "employer" })),
                            React.createElement("label", { htmlFor: "role" }, "Role"),
                            React.createElement(formik_1.Field, { as: "select", id: "role", name: "role" },
                                React.createElement("option", { value: "designer" }, "Designer"),
                                React.createElement("option", { value: "development" }, "Developer"),
                                React.createElement("option", { value: "product" }, "Product Manager"),
                                React.createElement("option", { value: "other" }, "Other")),
                            React.createElement("div", { className: "add-student-form__section__grid__buttons" },
                                React.createElement("button", { type: "reset" }, "Cancel"),
                                React.createElement("button", { type: "submit" }, "Submit")))))))));
};
exports["default"] = exports.AddForm;
