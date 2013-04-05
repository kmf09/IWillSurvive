using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

namespace Ecoregion.Handler
{
    public class Region_Handler
    {
        #region Constructors
        private Region_Handler()
        {
            #region Default Constructor. Private to prevent instantiation.
            #endregion
        }
        #endregion
        #region Methods
        private Biome GetBiomeInfo(int BiomeSelection)
        {
            #region Create a new Biome object using information from files pertaining to the selected biome
            int numLocations;
            string fileName = SelectBiomeFile(BiomeSelection);
            string biomeName = SelectBiomeName(BiomeSelection);
            FileStream inFile = File.Open(fileName, FileMode.Open);
            BinaryReader readIn = new BinaryReader(inFile);
            numLocations = readIn.ReadInt32();
            string[] LocationNames = new string[numLocations];
            int[][] LocationInfo = new int[numLocations][];
            int[][] SelectionInfo = new int[numLocations][];
            for (int index = 0; index < numLocations; index++)
            {
                LocationInfo[index] = new int[Constants.NUMCRITERIA];
                SelectionInfo[index] = new int[2];
            }
            for (int locationElement = 0; locationElement < numLocations; locationElement++)
            {
                LocationNames[locationElement] = readIn.ReadString();
                for (int criteriaElement = 0; criteriaElement < Constants.NUMCRITERIA; criteriaElement++)
                {
                    LocationInfo[locationElement][criteriaElement] = readIn.ReadInt32();
                }
                SelectionInfo[locationElement][0] = readIn.ReadInt32();
                SelectionInfo[locationElement][1] = readIn.ReadInt32();
            }
            Biome chosenBiome = new Biome(biomeName, LocationNames, LocationInfo, SelectionInfo);

            return (chosenBiome);
            #endregion
        }
        private string SelectBiomeFile(int BiomeSelection)
        {
            #region Select the file containing the location information for the selected biome
            string fileName = "";
            if (BiomeSelection == Constants.TEST)
            {
                fileName = "Test.eid";
            }
            if (BiomeSelection == Constants.ARCTIC)
            {
                fileName = "Arctic.eid";
            }
            else if (BiomeSelection == Constants.DESERT)
            {
                fileName = "Desert.eid";
            }
            else if (BiomeSelection == Constants.FOREST)
            {
                fileName = "Forest.eid";
            }
            else if (BiomeSelection == Constants.MOUNTAINS)
            {
                fileName = "Mountains.eid";
            }
            else if (BiomeSelection == Constants.PLAINS)
            {
                fileName = "Plains.eid";
            }
            else if (BiomeSelection == Constants.SWAMP)
            {
                fileName = "Swamp.eid";
            }
            else
            {
                throw new ArgumentOutOfRangeException("BiomeSelection", BiomeSelection,
                    "Biome Selected Invalid. Use biomes listed in Ecoregion.Handler.Constants.");
            }

            return (fileName);
            #endregion
        }
        private string SelectBiomeName(int BiomeSelection)
        {
            #region Select the name corresponding to the selected biome
            string biomeName = "";
            if (BiomeSelection == Constants.TEST)
            {
                biomeName = "Test";
            }
            if (BiomeSelection == Constants.ARCTIC)
            {
                biomeName = "Arctic";
            }
            else if (BiomeSelection == Constants.DESERT)
            {
                biomeName = "Desert";
            }
            else if (BiomeSelection == Constants.FOREST)
            {
                biomeName = "Forest";
            }
            else if (BiomeSelection == Constants.MOUNTAINS)
            {
                biomeName = "Mountains";
            }
            else if (BiomeSelection == Constants.PLAINS)
            {
                biomeName = "Plains";
            }
            else if (BiomeSelection == Constants.SWAMP)
            {
                biomeName = "Swamp";
            }
            else
            {
                throw new ArgumentOutOfRangeException("BiomeSelection", BiomeSelection,
                    "Biome Selected Invalid. Use biomes listed in Ecoregion.Handler.Constants.");
            }

            return (biomeName);
            #endregion
        }
        #endregion
    }
}
