using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Ecoregion.Handler
{
    class Biome
    {
        #region Fields
        private Ecoregion[] locations;
        private int maxSelectionValue;
        private string BiomeName;
        #endregion
        #region Constructors
        private Biome()
        {
            #region Default Constructor. Private to prevent instantiation without passed parameters
            #endregion
        }
        public Biome(string name, Ecoregion[] Locations)
        {
            #region Constructor for instantiation with a list of pre-instantiated Ecoregion objects
            locations = Locations;
            maxSelectionValue = Locations[Locations.Length - 1].GetRangeHigh();
            BiomeName = name;
            #endregion
        }
        public Biome(string name, string[] LocationNames, int[][] LocationCriteria, int[][] SelectionInfo)
        {
            #region Constructor for instantiation with information directly from a biome file
            int numLocations = LocationNames.Length;
            locations = new Ecoregion[numLocations];
            for (int index = 0; index < numLocations; index++)
            {
                locations[index] = new Ecoregion(LocationNames[index], LocationCriteria[index],
                    SelectionInfo[index][0], SelectionInfo[index][1]);
            }
            maxSelectionValue = SelectionInfo[numLocations - 1][1];
            BiomeName = name;
            #endregion
        }
        #endregion
        #region Methods
        public Ecoregion GetLocation(int LocationIndex)
        {
            #region Returns the Ecoregion object at index LocationIndex
            return (locations[LocationIndex]);
            #endregion
        }
        public Ecoregion GetNamedLocation(string LocationName)
        {
            #region Returns the Ecoregion object with the corresponding LocationName
            Ecoregion target = null;
            for (int index = 0; index < locations.Length && target != null; index++)
            {
                if (locations[index].GetName() == LocationName)
                    target = locations[index];
            }
            if (target == null)
            {
                throw new ArgumentException("Could not find any locations by the name " + LocationName + ".");
            }

            return (target);
            #endregion
        }
        public Ecoregion SelectLocation(int randomSelector)
        {
            #region Returns the Ecoregion object whose selection range the value of Random Selector lies within
            Ecoregion target = null;
            int modifiedSelector = randomSelector % maxSelectionValue;
            for (int index = 0; index < locations.Length && target == null; index++)
            {
                if (modifiedSelector > locations[index].GetRangeHigh() && modifiedSelector < locations[index].GetRangeLow())
                {
                    target = locations[index];
                }
            }
            return (target);
            #endregion
        }
        public Ecoregion[] GetAllLocations()
        {
            #region Returns all Ecoregion objects belonging to this biome
            return (locations);
            #endregion
        }
        public string GetBiomeName()
        {
            return (BiomeName);
        }
        #endregion
    }
}
