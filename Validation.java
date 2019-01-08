    public class Validation implements Initializable {
        @FXML
        JFXTextField emailAdress;

        @FXML
        JFXPasswordField passFirst, passSecond;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator();
            requiredFieldValidator.setMessage("EMPTY FIELD TEXT");

            ValidatorBase emailValidator = new ValidatorBase() {
                @Override
                protected void eval() {
                    setMessage("WRONG EMAIL TEXT");
                    if (emailAdress.getText().length() < 2 || !isValidEmailAddress(emailAdress.getText())) {
                        hasErrors.set(true);
                    } else hasErrors.set(false);
                }
            };

            emailAdress.getValidators().addAll(requiredFieldValidator, emailValidator);

            emailAdress.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue) {
                    emailAdress.validate();
                }
            });

            ValidatorBase lenghtPassword = new ValidatorBase() {
                @Override
                protected void eval() {
                    JFXPasswordField pass = (JFXPasswordField) getSrcControl();
                    setMessage("WRONG PASS LENGHT TEXT");
                    if (pass.getText().length() <= 3) {
                        hasErrors.set(true);
                    } else hasErrors.set(false);
                }
            };

            ValidatorBase equalPassword = new ValidatorBase() {
                @Override
                protected void eval() {
                    setMessage("PASS MISSMATCH");
                    if (!passFirst.getText().equals(passSecond.getText())) {
                        hasErrors.set(true);
                    } else hasErrors.set(false);
                }
            };

            passSecond.getValidators().addAll(requiredFieldValidator, lenghtPassword, equalPassword);
            passFirst.getValidators().addAll(requiredFieldValidator, lenghtPassword, equalPassword);

            passFirst.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue) {
                    passFirst.validate();
                    if (passSecond.getText().length() > 3) passSecond.validate();
                }
            });

            passSecond.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue) {
                    passSecond.validate();
                    if (passFirst.getText().length() > 3) passFirst.validate();
                }
            });
        }
    }
