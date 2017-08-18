/*
    validateForm.js validates all forms throughout the Mathtastic Games website.
    User: Holli
    Date: 8/11/2017
    Time: 11:50 PM
 */

var allPassed;

function resetColor(inputBox) {
    inputBox.style.backgroundColor = "#F2F2F2";
}

function validateBoxNotEmpty(inputBox) {
    var inputLength = inputBox.value.length;

    if (inputLength === 0) {
        inputBox.style.backgroundColor = "#FFFF00";
        allPassed = false;
    }
}

function validatePasswordConfirmation(passwordBox, confirmPasswordBox) {
    var password = passwordBox.value;
    var confirmPassword = confirmPasswordBox.value;

    if (password !== confirmPassword) {
        passwordBox.style.backgroundColor = "#FFFF00";
        confirmPasswordBox.style.backgroundColor = "#FFFF00";
        allPassed = false;
    }
}

function validateNumber(numberBox) {
    console.log("validateNumber()");
    var number = numberBox.value;

    if (isNaN(number)) {
        numberBox.style.backgroundColor = "#FFFF00";
        allPassed = false;
    }
}

function validateYear(yearBox) {
    var year = yearBox.value;

    if (/^\d{4}$/.test(year) === false) {
        yearBox.style.backgroundColor = "#FFFF00";
        allPassed = false;
    }
}

function validateDate(dayBox, monthBox, yearBox) {
    validateNumber(dayBox);
    validateNumber(monthBox);
    validateYear(yearBox);
}

function resetSignUpForm() {
    var firstName;
    var lastName;
    var email;
    var password;
    var confirmPassword;
    var month;
    var day;
    var year;

    firstName = document.getElementById("firstName");
    lastName = document.getElementById("lastName");
    email = document.getElementById("email");
    password = document.getElementById("password");
    confirmPassword = document.getElementById("confirmPassword");
    month = document.getElementById("month");
    day = document.getElementById("day");
    year = document.getElementById("year");

    resetColor(firstName);
    resetColor(lastName);
    resetColor(email);
    resetColor(password);
    resetColor(confirmPassword);
    resetColor(month);
    resetColor(day);
    resetColor(year);
}

function validateSignUpForm() {
    var firstName;
    var lastName;
    var email;
    var password;
    var confirmPassword;
    var day;
    var month;
    var year;

    firstName = document.getElementById("firstName");
    lastName = document.getElementById("lastName");
    email = document.getElementById("email");
    password = document.getElementById("password");
    confirmPassword = document.getElementById("confirmPassword");
    day = document.getElementById("day");
    month = document.getElementById("month");
    year = document.getElementById("year");

    allPassed = true;

    resetSignUpForm();

    validateBoxNotEmpty(firstName);
    validateBoxNotEmpty(lastName);
    validateBoxNotEmpty(email);
    validateBoxNotEmpty(password);
    validateBoxNotEmpty(confirmPassword);
    validatePasswordConfirmation(password, confirmPassword);
    validateDate(day, month, year);

    return allPassed;
}

function validateMyAccountForm() {
    var firstName;
    var lastName;
    var email;
    var month;
    var day;
    var year;
    var currentPassword;
    var newPassword;
    var confirmPassword;

    firstName = document.getElementById("firstName");
    lastName = document.getElementById("lastName");
    email = document.getElementById("email");
    day = document.getElementById("day");
    month = document.getElementById("month");
    year = document.getElementById("year");
    currentPassword = document.getElementById("currentPassword");
    newPassword = document.getElementById("newPassword");
    confirmPassword = document.getElementById("confirmPassword");

    allPassed = true;

    resetColor(day);
    resetColor(month);
    resetColor(year);
    resetColor(currentPassword);
    resetColor(newPassword);
    resetColor(confirmPassword);

    validateBoxNotEmpty(currentPassword);

    if (firstName.value.length === 0 && lastName.value.length === 0 && email.value.length === 0 && isNaN(day.value) && isNaN(month.value)
            && year.value.length === 0 && newPassword.value.length === 0 && confirmPassword.value.length === 0 || !allPassed) {
        return false;
    }

    if (!isNaN(day.value) || !isNaN(month.value) || year.value.length !== 0) {
        validateDate(day, month, year);
    }

    if (newPassword.value.length !== 0) {
        validateBoxNotEmpty(confirmPassword);
        validatePasswordConfirmation(newPassword, confirmPassword);
    }
    return allPassed;
}

function validateNumberEntered() {
    var childAnswer = document.getElementById("childAnswer");

    if (childAnswer.value.length === 0 || isNaN(childAnswer.value)) {
        return false;
    }
}