using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Ecoregion.Handler
{
    public class Ecoregion
    {
        #region Fields
        private int floraEdibility,
            floraToxicity,
            floraShelterScore,
            floraFirewoodScore,
            floraHostility,
            floraTraversability,
            floraCanopyCoverage,
            floraHerbScore,
            faunaEdibility,
            faunaDisease,
            faunaAggression,
            faunaVenom,
            faunaHuntability,
            faunaExploitability,
            faunaDensity,
            terrainRockiness,
            terrainElevation,
            terrainTopographics,
            terrainWaterSaturation,
            terrainHazards,
            climateLowTemps,
            climateHighTemps,
            climatePrecipitation,
            climateHazardousWeather,
            waterPrevalence,
            waterPotability,
            selectionRangeLow,
            selectionRangeHigh;
        private string locationName;
        #endregion
        #region Constructors
        protected Ecoregion()
        {
            #region Default Constructor. ONLY FOR TESTING PURPOSES
            locationName = "Test Location";
            selectionRangeLow = 0;
            selectionRangeHigh = 1;
            uint[] EcoCriteria = new uint[Constants.NUMCRITERIA];
            for (int index = 0; index < Constants.NUMCRITERIA; index++)
                EcoCriteria[index] = Constants.CRITERIONEXCEPTIONAL;
            InsertScores(EcoCriteria);
            #endregion
        }
        protected Ecoregion(string LocationName, int SelectorLow, int SelectorHigh)
        {
            #region Constructor only for testing purposes
            locationName = LocationName;
            selectionRangeHigh = SelectorHigh;
            selectionRangeLow = SelectorLow;
            int[] EcoCriteria = new int[Constants.NUMCRITERIA];
            for (int index = 0; index < Constants.NUMCRITERIA; index++)
                EcoCriteria[index] = Constants.CRITERIONEXCEPTIONAL;
            #endregion
        }
        public Ecoregion(string LocationName, int[] EcoCriteria, int SelectorLow, int SelectorHigh)
        {
            #region Constructor for normal use
            #region Verify all arguments valid
            for (int index = 0; index < Constants.NUMCRITERIA; index++)
            {
                if (EcoCriteria[index] > Constants.HIGHESTPOSSIBLESCORE || EcoCriteria[index] < Constants.LOWESTPOSSIBLESCORE)
                    throw new ArgumentOutOfRangeException("EcoCriteria[" + index + "]", EcoCriteria[index],
                        "All criterion values must be between "+ Constants.LOWESTPOSSIBLESCORE + 
                        " and " + Constants.HIGHESTPOSSIBLESCORE + ", inclusive.");
            }
            if (EcoCriteria.Length != Constants.NUMCRITERIA)
            {
                throw new ArgumentException("Ecoregion EcoCriteria array must have exactly " + Constants.NUMCRITERIA + 
                    " elements.", "EcoCriteria");
            }
            else if (LocationName.Length < Constants.MINLOCATIONNAMELENGTH || LocationName.Length > Constants.MAXLOCATIONNAMELENGTH)
            {
                throw new ArgumentOutOfRangeException("LocationName", LocationName,
                    "Ecoregion Location Name must be at least "+ Constants.MINLOCATIONNAMELENGTH
                    + " characters and no more than " + Constants.MAXLOCATIONNAMELENGTH + ".");
            }
            #endregion
            else
            {
                #region All arguments valid
                locationName = LocationName;
                selectionRangeHigh = SelectorHigh;
                selectionRangeLow = SelectorLow;
                InsertScores(EcoCriteria);
                #endregion
            }
            #endregion
        }
        #endregion
        #region Methods
        private void InsertScores(int[] EcoCriteria)
        {
            #region Fill EcoCriteria Scores
            #region Fill Flora Scores
            floraEdibility = EcoCriteria[Constants.FLORAEDIBILITY];
            floraToxicity = EcoCriteria[Constants.FLORATOXICITY];
            floraShelterScore = EcoCriteria[Constants.FLORASHELTERSCORE];
            floraFirewoodScore = EcoCriteria[Constants.FLORAFIREWOODSCORE];
            floraHostility = EcoCriteria[Constants.FLORAHOSTILITY];
            floraTraversability = EcoCriteria[Constants.FLORATRAVERSABILITY];
            floraCanopyCoverage = EcoCriteria[Constants.FLORACANOPYCOVERAGE];
            floraHerbScore = EcoCriteria[Constants.FLORAHERBSCORE];
            #endregion
            #region Fill Fauna Scores
            faunaEdibility = EcoCriteria[Constants.FAUNAEDIBILITY];
            faunaDisease = EcoCriteria[Constants.FAUNADISEASE];
            faunaAggression = EcoCriteria[Constants.FAUNAAGGRESSION];
            faunaVenom = EcoCriteria[Constants.FAUNAVENOM];
            faunaHuntability = EcoCriteria[Constants.FAUNAHUNTABILITY];
            faunaExploitability = EcoCriteria[Constants.FAUNAEXPLOITABILITY];
            faunaDensity = EcoCriteria[Constants.FAUNADENSITY];
            #endregion
            #region Fill Terrain Scores
            terrainRockiness = EcoCriteria[Constants.TERRAINROCKINESS];
            terrainElevation = EcoCriteria[Constants.TERRAINELEVATION];
            terrainTopographics = EcoCriteria[Constants.TERRAINTOPOGRAPHICS];
            terrainWaterSaturation = EcoCriteria[Constants.TERRAINWATERSATURATION];
            terrainHazards = EcoCriteria[Constants.TERRAINHAZARDS];
            #endregion
            #region Fill Climate Scores
            climateLowTemps = EcoCriteria[Constants.CLIMATELOWTEMPS];
            climateHighTemps = EcoCriteria[Constants.CLIMATEHIGHTEMPS];
            climatePrecipitation = EcoCriteria[Constants.CLIMATEPRECIPITATION];
            climateHazardousWeather = EcoCriteria[Constants.CLIMATEHAZARDOUSWEATHER];
            #endregion
            #region Fill Water Scores
            waterPrevalence = EcoCriteria[Constants.WATERPREVALENCE];
            waterPotability = EcoCriteria[Constants.WATERPOTABILITY];
            #endregion
            #endregion
        }

        #region Getters
        public int[] GetScores()
        {
            int[] EcoCriteria = new int[Constants.NUMCRITERIA];
            #region Get EcoCriteria
            #region Get Flora Criteria
            EcoCriteria[Constants.FLORAEDIBILITY] = floraEdibility;
            EcoCriteria[Constants.FLORATOXICITY] = floraToxicity;
            EcoCriteria[Constants.FLORASHELTERSCORE] = floraShelterScore;
            EcoCriteria[Constants.FLORAFIREWOODSCORE] = floraFirewoodScore;
            EcoCriteria[Constants.FLORAHOSTILITY] = floraHostility;
            EcoCriteria[Constants.FLORATRAVERSABILITY] = floraTraversability;
            EcoCriteria[Constants.FLORACANOPYCOVERAGE] = floraCanopyCoverage;
            EcoCriteria[Constants.FLORAHERBSCORE] = floraHerbScore;
            #endregion
            #region Get Fauna Criteria
            EcoCriteria[Constants.FAUNAEDIBILITY] = faunaEdibility;
            EcoCriteria[Constants.FAUNADISEASE] = faunaDisease;
            EcoCriteria[Constants.FAUNAAGGRESSION] = faunaAggression;
            EcoCriteria[Constants.FAUNAVENOM] = faunaVenom;
            EcoCriteria[Constants.FAUNAHUNTABILITY] = faunaHuntability;
            EcoCriteria[Constants.FAUNAEXPLOITABILITY] = faunaExploitability;
            EcoCriteria[Constants.FAUNADENSITY] = faunaDensity;
            #endregion
            #region Get Terrain Criteria
            EcoCriteria[Constants.TERRAINROCKINESS] = terrainRockiness;
            EcoCriteria[Constants.TERRAINELEVATION] = terrainElevation;
            EcoCriteria[Constants.TERRAINTOPOGRAPHICS] = terrainTopographics;
            EcoCriteria[Constants.TERRAINWATERSATURATION] = terrainWaterSaturation;
            EcoCriteria[Constants.TERRAINHAZARDS] = terrainHazards;
            #endregion
            #region Get Climate Criteria
            EcoCriteria[Constants.CLIMATELOWTEMPS] = climateLowTemps;
            EcoCriteria[Constants.CLIMATEHIGHTEMPS] = climateHighTemps;
            EcoCriteria[Constants.CLIMATEPRECIPITATION] = climatePrecipitation;
            EcoCriteria[Constants.CLIMATEHAZARDOUSWEATHER] = climateHazardousWeather;
            #endregion
            #region Get Water Criteria
            EcoCriteria[Constants.WATERPREVALENCE] = waterPrevalence;
            EcoCriteria[Constants.WATERPOTABILITY] = waterPotability;
            #endregion
            #endregion
            return (EcoCriteria);
        }
        public string GetName()
        {
            return (locationName);
        }
        public int[] GetRange()
        {
            int[] range = new int[2];
            range[0] = selectionRangeLow;
            range[1] = selectionRangeHigh;

            return (range);
        }
        public int GetRangeLow()
        {
            return (selectionRangeLow);
        }
        public int GetRangeHigh()
        {
            return (selectionRangeHigh);
        }
        #endregion
        #region Setters
        public void AlterScore(int Criterion, int Score)
        {
            #region Check Entry Valid
            if (Score <= Constants.HIGHESTPOSSIBLESCORE && Score >= Constants.LOWESTPOSSIBLESCORE)
            {
                #region Entry Valid
                #region Alter Appropriate Criterion
                if (Criterion == Constants.FLORAEDIBILITY)
                    floraEdibility = Score;
                else if (Criterion == Constants.FLORATOXICITY)
                    floraToxicity = Score;
                else if (Criterion == Constants.FLORASHELTERSCORE)
                    floraShelterScore = Score;
                else if (Criterion == Constants.FLORAFIREWOODSCORE)
                    floraFirewoodScore = Score;
                else if (Criterion == Constants.FLORAHOSTILITY)
                    floraHostility = Score;
                else if (Criterion == Constants.FLORATRAVERSABILITY)
                    floraTraversability = Score;
                else if (Criterion == Constants.FLORACANOPYCOVERAGE)
                    floraCanopyCoverage = Score;
                else if (Criterion == Constants.FLORAHERBSCORE)
                    floraHerbScore = Score;
                else if (Criterion == Constants.FAUNAEDIBILITY)
                    faunaEdibility = Score;
                else if (Criterion == Constants.FAUNADISEASE)
                    faunaDisease = Score;
                else if (Criterion == Constants.FAUNAAGGRESSION)
                    faunaAggression = Score;
                else if (Criterion == Constants.FAUNAVENOM)
                    faunaVenom = Score;
                else if (Criterion == Constants.FAUNAHUNTABILITY)
                    faunaHuntability = Score;
                else if (Criterion == Constants.FAUNAEXPLOITABILITY)
                    faunaExploitability = Score;
                else if (Criterion == Constants.FAUNADENSITY)
                    faunaDensity = Score;
                else if (Criterion == Constants.TERRAINROCKINESS)
                    terrainRockiness = Score;
                else if (Criterion == Constants.TERRAINELEVATION)
                    terrainElevation = Score;
                else if (Criterion == Constants.TERRAINTOPOGRAPHICS)
                    terrainTopographics = Score;
                else if (Criterion == Constants.TERRAINWATERSATURATION)
                    terrainWaterSaturation = Score;
                else if (Criterion == Constants.TERRAINHAZARDS)
                    terrainHazards = Score;
                else if (Criterion == Constants.CLIMATELOWTEMPS)
                    climateLowTemps = Score;
                else if (Criterion == Constants.CLIMATEHIGHTEMPS)
                    climateHighTemps = Score;
                else if (Criterion == Constants.CLIMATEPRECIPITATION)
                    climatePrecipitation = Score;
                else if (Criterion == Constants.CLIMATEHAZARDOUSWEATHER)
                    climateHazardousWeather = Score;
                else if (Criterion == Constants.WATERPREVALENCE)
                    waterPrevalence = Score;
                else if (Criterion == Constants.WATERPOTABILITY)
                    waterPotability = Score;
                #endregion
                #endregion
            }
            else
            {
                #region Entry Invalid
                throw new ArgumentOutOfRangeException("Score", Score, "Score must be between " +
                    Constants.LOWESTPOSSIBLESCORE + " and " + Constants.HIGHESTPOSSIBLESCORE + " inclusive.");
                #endregion
            }
            #endregion
            return;
        }
        public void AlterAllScores(int[] EcoCriteria)
        {
            int index;
            #region Check Number of Criteria Correct
            if (EcoCriteria.Length != Constants.NUMCRITERIA)
                #region Number of Criteria Incorrect
                throw new ArgumentException("EcoCriteria array argument must have " + Constants.NUMCRITERIA + " elements");
                #endregion
            else
            {
                #region Number of Criteria Correct
                #region Check All Entries Valid
                for (index = 0; index < Constants.NUMCRITERIA; index++)
                {
                    if (EcoCriteria[index] > Constants.HIGHESTPOSSIBLESCORE || EcoCriteria[index] < Constants.LOWESTPOSSIBLESCORE)
                    {
                        break;
                        throw new ArgumentOutOfRangeException("EcoCriteria[" + index + "]", EcoCriteria[index],
                            "Scores must be between " + Constants.LOWESTPOSSIBLESCORE + " and " +
                            Constants.HIGHESTPOSSIBLESCORE + ", inclusive.");
                    }
                }
                #endregion
                #region If All Entries Valid, Insert Scores
                if (index != Constants.NUMCRITERIA)
                    InsertScores(EcoCriteria);
                #endregion
                #endregion
            }
            #endregion
            return;
        }
        public void AlterName(string newName)
        {
            locationName = newName;
        }
        #endregion
        #endregion
    }
}