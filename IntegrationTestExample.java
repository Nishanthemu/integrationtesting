import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IntegrationTestExample {

    private LoginPage loginPage;
    private AdminPage adminPage;

    @Before
    public void setUp() {
        SwingUtilities.invokeLater(() -> {
            loginPage = new LoginPage();
            adminPage = new AdminPage();
        });
        try {
            // Wait for the GUI to be visible before starting the tests
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        SwingUtilities.invokeLater(() -> {
            loginPage.dispose();
            adminPage.dispose();
        });
    }

    @Test
    public void testSuccessfulLogin() {
        JTextField usernameField = loginPage.getUsernameField();
        JPasswordField passwordField = loginPage.getPasswordField();
        JButton loginButton = loginPage.getLoginButton();

        SwingUtilities.invokeLater(() -> {
            usernameField.setText("admin");
            passwordField.setText("password");
            loginButton.doClick();
        });

        try {
            // Wait for the login process to complete
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertFalse(loginPage.isVisible());
        assertTrue(adminPage.isVisible());
    }

    @Test
    public void testInvalidLogin() {
        JTextField usernameField = loginPage.getUsernameField();
        JPasswordField passwordField = loginPage.getPasswordField();
        JButton loginButton = loginPage.getLoginButton();

        SwingUtilities.invokeLater(() -> {
            usernameField.setText("invalid");
            passwordField.setText("wrongpassword");
            loginButton.doClick();
        });

        try {
            // Wait for the login process to complete
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(loginPage.isVisible());
        assertFalse(adminPage.isVisible());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            IntegrationTestExample integrationTest = new IntegrationTestExample();
            integrationTest.setUp();
            integrationTest.testSuccessfulLogin();
            integrationTest.testInvalidLogin();
            integrationTest.tearDown();
        });
    }
}
