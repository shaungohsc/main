package seedu.travel.storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.travel.commons.core.LogsCenter;
import seedu.travel.commons.exceptions.DataConversionException;
import seedu.travel.model.ReadOnlyCountryChart;
import seedu.travel.model.ReadOnlyRatingChart;
import seedu.travel.model.ReadOnlyTravelBuddy;
import seedu.travel.model.ReadOnlyUserPrefs;
import seedu.travel.model.ReadOnlyYearChart;
import seedu.travel.model.UserPrefs;

/**
 * Manages storage of TravelBuddy data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TravelBuddyStorage travelBuddyStorage;
    private UserPrefsStorage userPrefsStorage;
    private ChartBookStorage chartBookStorage;


    public StorageManager(TravelBuddyStorage travelBuddyStorage, UserPrefsStorage userPrefsStorage,
                          ChartBookStorage chartBookStorage) {
        super();
        this.travelBuddyStorage = travelBuddyStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.chartBookStorage = chartBookStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ TravelBuddy methods ==============================

    @Override
    public Path getTravelBuddyFilePath() {
        return travelBuddyStorage.getTravelBuddyFilePath();
    }

    @Override
    public Path getCountryChartFilePath() {
        return chartBookStorage.getCountryChartFilePath();
    }

    @Override
    public Path getRatingChartFilePath() {
        return chartBookStorage.getRatingChartFilePath();
    }

    @Override
    public Path getYearChartFilePath() {
        return chartBookStorage.getYearChartFilePath();
    }

    @Override
    public List<ReadOnlyCountryChart> readCountryChart() throws FileNotFoundException {
        return readCountryChart(chartBookStorage.getCountryChartFilePath());
    }

    @Override
    public List<ReadOnlyCountryChart> readCountryChart(Path filePath) throws FileNotFoundException {
        logger.fine("Attempting to read data from file: " + filePath);
        return chartBookStorage.readCountryChart(filePath);
    }

    @Override
    public List<ReadOnlyRatingChart> readRatingChart() throws FileNotFoundException {
        return readRatingChart(chartBookStorage.getRatingChartFilePath());
    }

    @Override
    public List<ReadOnlyRatingChart> readRatingChart(Path filePath) throws FileNotFoundException {
        logger.fine("Attempting to read data from file: " + filePath);
        return chartBookStorage.readRatingChart(filePath);
    }

    @Override
    public List<ReadOnlyYearChart> readYearChart() throws FileNotFoundException {
        return readYearChart(chartBookStorage.getYearChartFilePath());
    }

    @Override
    public List<ReadOnlyYearChart> readYearChart(Path filePath) throws FileNotFoundException {
        logger.fine("Attempting to read data from file: " + filePath);
        return chartBookStorage.readYearChart(filePath);
    }

    @Override
    public Optional<ReadOnlyTravelBuddy> readTravelBuddy() throws DataConversionException, IOException {
        return readTravelBuddy(travelBuddyStorage.getTravelBuddyFilePath());
    }

    @Override
    public Optional<ReadOnlyTravelBuddy> readTravelBuddy(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return travelBuddyStorage.readTravelBuddy(filePath);
    }

    @Override
    public void saveTravelBuddy(ReadOnlyTravelBuddy travelBuddy) throws IOException {
        saveTravelBuddy(travelBuddy, travelBuddyStorage.getTravelBuddyFilePath());
    }

    @Override
    public void saveTravelBuddy(ReadOnlyTravelBuddy travelBuddy, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        travelBuddyStorage.saveTravelBuddy(travelBuddy, filePath);
    }

    @Override
    public void saveCountryChart(ReadOnlyCountryChart countryChart) {
        saveCountryChart(countryChart, chartBookStorage.getCountryChartFilePath());
    }

    @Override
    public void saveCountryChart(ReadOnlyCountryChart countryChart, Path filePath) {
        logger.fine("Attempting to write to country data file: " + filePath);
        chartBookStorage.saveCountryChart(countryChart, filePath);
    }

    @Override
    public void saveRatingChart(ReadOnlyRatingChart ratingChart) {
        saveRatingChart(ratingChart, chartBookStorage.getRatingChartFilePath());
    }

    @Override
    public void saveRatingChart(ReadOnlyRatingChart ratingChart, Path filePath) {
        logger.fine("Attempting to write to rating data file: " + filePath);
        chartBookStorage.saveRatingChart(ratingChart, filePath);
    }

    @Override
    public void saveYearChart(ReadOnlyYearChart yearChart) {
        saveYearChart(yearChart, chartBookStorage.getYearChartFilePath());
    }

    @Override
    public void saveYearChart(ReadOnlyYearChart yearChart, Path filePath) {
        logger.fine("Attempting to write to year data file: " + filePath);
        chartBookStorage.saveYearChart(yearChart, filePath);
    }

    @Override
    public void backupTravelBuddy(ReadOnlyTravelBuddy travelBuddy) throws IOException {
        travelBuddyStorage.backupTravelBuddy(travelBuddy);
    }

}
