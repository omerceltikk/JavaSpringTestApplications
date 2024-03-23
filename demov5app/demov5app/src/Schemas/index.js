import * as yup from "yup";
const passwordRules = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,}$/;

export const SignupSchema = yup.object().shape({
  username: yup
    .string()
    .min(3, "Username must be longer than 3 characters.")
    .required("Username Required!"),
  password: yup
    .string()
    .min(3, "Please enter min 5 characters.")
    .matches(passwordRules, {
      message:
        "Password must be contains 1 Uppercase 1 Lowercase characters and 1 number.  ",
    })
    .required("Password Required!"),
  confirmPassword: yup
    .string()
    .oneOf([yup.ref("password")], "Password did not confirmed!")
    .required("Password Confirm is Required."),
  image: yup
    .string()
});

export const LoginSchema = yup.object().shape({
  username: yup
    .string()
    .min(3, "Username must be longer than 3 characters.")
    .required("Username Required!"),
  password: yup
    .string()
    .min(3, "Please enter min 5 characters.")
    .required("Password Required!"),
});
export const PostSchema = yup.object().shape({
  title: yup.string(),
  text: yup.string()
})
