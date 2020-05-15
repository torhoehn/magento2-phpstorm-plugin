/*
 * Copyright © Magento, Inc. All rights reserved.
 * See COPYING.txt for license details.
 */

package com.magento.idea.magento2plugin.actions.generation.dialog.validator;

import com.magento.idea.magento2plugin.actions.generation.data.CronGroupXmlData;
import com.magento.idea.magento2plugin.actions.generation.dialog.NewCronGroupDialog;
import com.magento.idea.magento2plugin.bundles.CommonBundle;
import com.magento.idea.magento2plugin.bundles.ValidatorBundle;
import com.magento.idea.magento2plugin.util.RegExUtil;
import javax.swing.JOptionPane;

@SuppressWarnings({"PMD.OnlyOneReturn", "PMD.NonThreadSafeSingleton"})
public class NewCronGroupValidator {
    private static final String MUST_NOT_BE_NEGATIVE = "validator.mustNotBeNegative";
    private static NewCronGroupValidator instance;
    private final ValidatorBundle validatorBundle;
    private final CommonBundle commonBundle;

    /**
     * Get instance of a class.
     *
     * @return NewCronGroupValidator
     */
    public static NewCronGroupValidator getInstance() {
        if (null == instance) {
            instance = new NewCronGroupValidator();
        }

        return instance;
    }

    /**
     * New CRON group validator constructor.
     */
    public NewCronGroupValidator() {
        this.validatorBundle = new ValidatorBundle();
        this.commonBundle = new CommonBundle();
    }

    /**
     * Validate whenever new CRON group dialog data is ready for generation.
     *
     * @param dialog Cron Group Dialog
     * @return boolean
     */
    public boolean validate(final NewCronGroupDialog dialog) {
        final CronGroupXmlData cronGroupXmlData = dialog.getCronGroupXmlData();

        return this.isGroupNameValid(cronGroupXmlData)
                && isScheduleGenerateEveryValid(cronGroupXmlData)
                && isScheduleAheadForValid(cronGroupXmlData)
                && isScheduleLifetimeValid(cronGroupXmlData)
                && isHistoryCleanupEveryValid(cronGroupXmlData)
                && isHistorySuccessLifetimeValid(cronGroupXmlData)
                && isHistoryFailureLifetimeValid(cronGroupXmlData);
    }

    private boolean isGroupNameValid(final CronGroupXmlData cronGroupXmlData) {
        final String cronGroupName = cronGroupXmlData.getGroupName();

        if (cronGroupName.length() == 0) {
            final String errorMessage = validatorBundle.message(
                    "validator.notEmpty",
                    "Cron Group Name"
            );
            JOptionPane.showMessageDialog(
                    null,
                    errorMessage,
                    getErrorTitle(),
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        if (!cronGroupName.matches(RegExUtil.IDENTIFIER)) {
            final String errorMessage = validatorBundle.message(
                    "validator.identifier",
                    "Cron Group Name"
            );
            JOptionPane.showMessageDialog(
                    null,
                    errorMessage,
                    getErrorTitle(),
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        return true;
    }

    private boolean isScheduleGenerateEveryValid(final CronGroupXmlData cronGroupXmlData) {
        if (cronGroupXmlData.getScheduleGenerateEvery() == null) {
            return true;
        }

        if (cronGroupXmlData.getScheduleGenerateEvery() <= 0) {
            final String errorMessage = validatorBundle.message(
                    MUST_NOT_BE_NEGATIVE,
                    "schedule_generate_every option"
            );
            JOptionPane.showMessageDialog(
                    null,
                    errorMessage,
                    getErrorTitle(),
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        return true;
    }

    private boolean isScheduleAheadForValid(final CronGroupXmlData cronGroupXmlData) {
        if (cronGroupXmlData.getScheduleAheadFor() == null) {
            return true;
        }

        if (cronGroupXmlData.getScheduleAheadFor() <= 0) {
            final String errorMessage = validatorBundle.message(
                    MUST_NOT_BE_NEGATIVE,
                    "schedule_ahead_for option"
            );
            JOptionPane.showMessageDialog(
                    null,
                    errorMessage,
                    getErrorTitle(),
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        return true;
    }

    private boolean isScheduleLifetimeValid(final CronGroupXmlData cronGroupXmlData) {
        if (cronGroupXmlData.getScheduleLifetime() == null) {
            return true;
        }

        if (cronGroupXmlData.getScheduleLifetime() <= 0) {
            final String errorMessage = validatorBundle.message(
                    MUST_NOT_BE_NEGATIVE,
                    "schedule_lifetime option"
            );
            JOptionPane.showMessageDialog(
                    null,
                    errorMessage,
                    getErrorTitle(),
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        return true;
    }

    private boolean isHistoryCleanupEveryValid(final CronGroupXmlData cronGroupXmlData) {
        if (cronGroupXmlData.getHistoryCleanupEvery() == null) {
            return true;
        }

        if (cronGroupXmlData.getHistoryCleanupEvery() <= 0) {
            final String errorMessage = validatorBundle.message(
                    MUST_NOT_BE_NEGATIVE,
                    "history_cleanup_every option"
            );
            JOptionPane.showMessageDialog(
                    null,
                    errorMessage,
                    getErrorTitle(),
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        return true;
    }

    private boolean isHistorySuccessLifetimeValid(final CronGroupXmlData cronGroupXmlData) {
        if (cronGroupXmlData.getHistorySuccessLifetime() == null) {
            return true;
        }

        if (cronGroupXmlData.getHistorySuccessLifetime() <= 0) {
            final String errorMessage = validatorBundle.message(
                    MUST_NOT_BE_NEGATIVE,
                    "history_success_lifetime option"
            );
            JOptionPane.showMessageDialog(
                    null,
                    errorMessage,
                    getErrorTitle(),
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        return true;
    }

    private boolean isHistoryFailureLifetimeValid(final CronGroupXmlData cronGroupXmlData) {
        if (cronGroupXmlData.getHistoryFailureLifetime() == null) {
            return true;
        }

        if (cronGroupXmlData.getHistoryFailureLifetime() <= 0) {
            final String errorMessage = validatorBundle.message(
                    MUST_NOT_BE_NEGATIVE,
                    "history_failure_lifetime option"
            );
            JOptionPane.showMessageDialog(
                    null,
                    errorMessage,
                    getErrorTitle(),
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        return true;
    }

    private String getErrorTitle() {
        return commonBundle.message("common.error");
    }
}