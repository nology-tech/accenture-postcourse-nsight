import "./AddForm.css";
import { Formik, Form, Field } from "formik";
import * as yup from "yup";

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

const personSchema = yup.object({
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
    .oneOf(["designer", "development", "product", "other"], "Invalid Job Role"),
});

type Person = yup.InferType<typeof personSchema>;

export const AddForm = () => {
  const initialValues: Person = {
    firstName: "",
    lastName: "",
    dateOfBirth: new Date("1995-12-17T03:24:00"),
    email: "",
    mobileNumber: "",
    dateOfGraduation: new Date("1995-12-17T03:24:00"),
    employed: false,
    employer: "",
    role: "",
  };

  return (
    <div className="add-student-form">
      <header>
        <h1>Add Student</h1>
      </header>

      <Formik
        initialValues={initialValues}
        onSubmit={(values, actions) => {
          console.log({ values, actions });
          alert(JSON.stringify(values, null, 2));
          actions.setSubmitting(false);
        }}
      >
        <Form>
          <section className="add-student-form__section">
            <div className="add-student-form__section__grid">
              <div>
                <label htmlFor="firstName">First Name</label>
                <Field id="firstName" name="firstName" type="text" />

                <label htmlFor="lastName">First Name</label>
                <Field id="lastName" name="lastName" type="text" />

                <label htmlFor="dateOfBirth">Date of Birth</label>
                <Field id="dateOfBirth" name="dateOfBirth" type="date" />

                <label htmlFor="email">E-mail</label>
                <Field id="email" name="email" type="text" />

                <label htmlFor="mobileNumber">Mobile number</label>
                <Field id="mobileNumber" name="mobileNumber" type="text" />

                <label htmlFor="dateOfGraduation">Graduation date</label>
                <Field
                  id="dateOfGraduation"
                  name="dateOfGraduation"
                  type="date"
                />
              </div>
              <div>
                <div>
                  <label htmlFor="employed">Employed</label>
                  <Field id="employed" name="employed" type="checkbox" />
                </div>

                <div>
                  <label htmlFor="employer">Employer</label>
                  <Field id="employer" name="employer" />
                </div>

                <label htmlFor="role">Role</label>
                <Field as="select" id="role" name="role">
                  <option value="designer">Designer</option>
                  <option value="development">Developer</option>
                  <option value="product">Product Manager</option>
                  <option value="other">Other</option>
                </Field>
                <div className="add-student-form__section__grid__buttons">
                  <button type="reset">Cancel</button>
                  <button type="submit">Submit</button>
                </div>
              </div>
            </div>
          </section>
        </Form>
      </Formik>
    </div>
  );
};

export default AddForm;
